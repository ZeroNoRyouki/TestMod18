package zero.mods.testmod18.common.multiblock;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zero.mods.testmod18.common.multiblock.tile.MightyFurnaceIOPortTileEntity;
import zero.mods.testmod18.common.multiblock.tile.MightyFurnacePowerTileEntity;
import zero.mods.testmod18.common.multiblock.tile.MightyFurnaceTileEntity;
import zero.mods.zerocore.common.blocks.ModBlockWithState;
import zero.mods.zerocore.common.multiblock.IMultiblockPart;
import zero.mods.zerocore.common.multiblock.MultiblockControllerBase;


public abstract class MightyFurnaceBlockBase extends ModBlockWithState implements ITileEntityProvider {

    //public static final PropertyBool ASSEMBLED = PropertyBool.create("assembledMB");


    @Override
    public TileEntity createNewTileEntity(World world, int meta) {

        switch (this._myType) {

            default:
                return new MightyFurnaceTileEntity();

            case Power:
                return new MightyFurnacePowerTileEntity();

            case Input:
                return new MightyFurnaceIOPortTileEntity(true);

            case Output:
                return new MightyFurnaceIOPortTileEntity(false);
        }
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos position, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {

        // base??

        if (player.isSneaking())
            return false;

        MultiblockControllerBase controller = this.getMultiblockController(world, position);

        if ((null != controller) && (null == player.getCurrentEquippedItem())) {

            Exception ex = controller.getLastValidationException();

            if (null != ex) {

                player.addChatMessage(new ChatComponentText(ex.getMessage()));
                return true;
            }
        }

        return false;
    }

    protected IMultiblockPart getMultiblockPartAt(IBlockAccess world, BlockPos position) {

        TileEntity te = world.getTileEntity(position);

        return te instanceof IMultiblockPart ? (IMultiblockPart)te : null;
    }

    protected MultiblockControllerBase getMultiblockController(IBlockAccess world, BlockPos position) {

        IMultiblockPart part = this.getMultiblockPartAt(world, position);

        return part.getMultiblockController();
    }


    protected MightyFurnaceBlockBase(String name, MightyFurnaceBlockType blockType) {

        super(name, Material.iron, false);
        this._myType = blockType;
    }

    @Override
    protected void initBlock() {

        //this.setDefaultState(this.blockState.getBaseState().withProperty(PARTTYPE, PartType.Wall).withProperty(POSITION, PartPosition.Interior).withProperty(ASSEMBLED, Boolean.FALSE));

        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(this.soundTypeMetal);

        this.registerBlockTileEntity(MightyFurnaceTileEntity.class, MightyFurnaceBlockType.Wall.toMeta());
        this.registerBlockTileEntity(MightyFurnacePowerTileEntity.class, MightyFurnaceBlockType.Power.toMeta());
        this.registerBlockTileEntity(MightyFurnaceIOPortTileEntity.class, MightyFurnaceBlockType.Output.toMeta());
    }


    private MightyFurnaceBlockType _myType;


}
