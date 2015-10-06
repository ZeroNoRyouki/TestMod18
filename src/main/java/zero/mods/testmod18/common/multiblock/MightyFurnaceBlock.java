package zero.mods.testmod18.common.multiblock;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.mods.testmod18.common.blocks.BlockWithState;
import zero.mods.testmod18.common.blocks.tile.TileDate;
import zero.mods.testmod18.common.multiblock.tile.MightyFurnaceIOPortTileEntity;
import zero.mods.testmod18.common.multiblock.tile.MightyFurnacePowerTileEntity;
import zero.mods.testmod18.common.multiblock.tile.MightyFurnaceTileEntity;
import zero.mods.zerocore.common.blocks.ModBlockWithState;
import zero.mods.zerocore.common.multiblock.IMultiblockPart;
import zero.mods.zerocore.common.multiblock.MultiblockControllerBase;
import zero.mods.zerocore.common.multiblock.rectangular.PartPosition;

import java.util.List;
import java.util.Map;

public class MightyFurnaceBlock extends ModBlockWithState implements ITileEntityProvider {

    public static final PropertyBool FORMED = PropertyBool.create("formed");
    public static final PropertyEnum POSITION = PartPosition.createProperty("position");
    public static final PropertyEnum PARTTYPE = PropertyEnum.create("parttype", PartType.class);


    public MightyFurnaceBlock(String name) {

        super(name, Material.iron, true);
        this._subBlocks = null;
    }

    /**
     * Get the damage value that this Block should drop
     */
    public int damageDropped(IBlockState state) {

        return this.getMetaFromState(state);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {

        int length;

        if (null == this._subBlocks) {

            PartType[] types = PartType.values();

            this._subBlocks = new ItemStack[length = types.length];

            for (int i = 0; i < length; ++i)
                this._subBlocks[i] = new ItemStack(item, 1, types[i].ordinal());

        } else
            length = this._subBlocks.length;

        for (int i = 0; i < length; ++i)
            list.add(this._subBlocks[i]);
    }

    @Override
    public String getBlockNameStateSuffix(ItemStack stack) {

        return String.format("%d", stack.getItemDamage());
    }

    @SideOnly(Side.CLIENT)
    protected void getSubBlocksNamesMap(Map<Integer, String> map) {

        for (int meta = 0; meta < 16; ++meta)
            map.put(meta, String.format("mightyfurnacepart_%d", meta + 1));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {

        PartType type = (PartType)state.getValue(PARTTYPE);

        return type.ordinal();
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {

        return this.getDefaultState().withProperty(PARTTYPE, PartType.getTypeFromMeta(meta));
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {

        boolean formed = false;
        PartPosition position = PartPosition.Interior;

        TileEntity te = world.getTileEntity(pos);

        if (te instanceof MightyFurnaceTileEntity) {

            MightyFurnaceTileEntity part = (MightyFurnaceTileEntity)te;
            MultiblockControllerBase controller = part.getMultiblockController();

            position = part.getPartPosition();
            formed = (null != controller) ? controller.isAssembled() : false;
        }

        return state.withProperty(POSITION, position).withProperty(FORMED, Boolean.valueOf(formed));
    }

    @Override
    protected BlockState createBlockState() {

        return new BlockState(this, new IProperty[] {PARTTYPE, POSITION, FORMED});
    }

    @Override
    protected void initBlock() {

        this.setDefaultState(this.blockState.getBaseState().withProperty(PARTTYPE, PartType.Wall).withProperty(POSITION, PartPosition.Interior).withProperty(FORMED, Boolean.FALSE));

        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(this.soundTypeMetal);

        this.registerBlockTileEntity(MightyFurnaceTileEntity.class, PartType.Wall.toMeta());
        this.registerBlockTileEntity(MightyFurnacePowerTileEntity.class, PartType.Power.toMeta());
        this.registerBlockTileEntity(MightyFurnaceIOPortTileEntity.class, PartType.Output.toMeta());
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        PartType type = PartType.getTypeFromMeta(meta);

        switch (type) {

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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (player.isSneaking())
            return false;

        TileEntity te = world.getTileEntity(pos);

        if (te instanceof IMultiblockPart) {

            IMultiblockPart part = (IMultiblockPart)te;
            MultiblockControllerBase controller = part.getMultiblockController();

            if ((null != controller) && (null == player.getCurrentEquippedItem())) {

                Exception ex = controller.getLastValidationException();

                if (null != ex) {

                    player.addChatMessage(new ChatComponentText(ex.getMessage()));
                    return true;
                }
            }
        }

        return false;
    }

    public enum PartType implements IStringSerializable {

        Wall,
        Power,
        Input,
        Output;

        public int toMeta() {

            return this.ordinal();
        }

        @Override
        public String getName() {

            return this.toString();
        }

        public static PartType getTypeFromMeta(int meta) {

            PartType[] values = PartType.values();

            return ((meta >= 0) && (meta < values.length)) ? values[meta] : PartType.Wall;
        }

    }





    private ItemStack[] _subBlocks;




}
