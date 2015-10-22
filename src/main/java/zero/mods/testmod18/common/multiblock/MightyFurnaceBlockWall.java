package zero.mods.testmod18.common.multiblock;

import com.google.common.collect.Lists;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import zero.mods.testmod18.common.multiblock.tile.MightyFurnaceTileEntity;
import zero.mods.zerocore.common.helpers.CodeHelper;
import zero.mods.zerocore.common.lib.BlockFacings;
import zero.mods.zerocore.common.lib.PropertyBlockFacings;
import zero.mods.zerocore.common.multiblock.IMultiblockPart;

import java.util.ArrayList;


public class MightyFurnaceBlockWall extends MightyFurnaceBlockBase {

    public MightyFurnaceBlockWall(String name) {

        super(name, MightyFurnaceBlockType.Wall);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos position, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {

        IMultiblockPart tile = this.getMultiblockPartAt(world, position);

        if (tile instanceof MightyFurnaceTileEntity) {

            MightyFurnaceTileEntity part = (MightyFurnaceTileEntity)tile;
            MightyFurnaceController controller = (MightyFurnaceController)part.getMultiblockController();

            if (null == controller)
                FMLLog.warning("WALL - got null controller!");

            if ((null != controller) && controller.isAssembled()) {

                /*
                controller.switchActive();
                */

                if (CodeHelper.calledByLogicalServer(world)) {

                    world.markBlockForUpdate(position);

                    int stored = controller.getEnergyStored(EnumFacing.EAST);
                    player.addChatMessage(new ChatComponentText(String.format("MB - energy stored = %d", stored)));

                    return true;
                }
            }
        }

        return super.onBlockActivated(world, position, state, player, side, hitX, hitY, hitZ);
    }

    @Override
    public String getBlockNameStateSuffix(ItemStack stack) {
        return null;
    }

    @Override
    public int getMetaFromState(IBlockState state) {

        return 0;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos position) {

        IMultiblockPart part = this.getMultiblockPartAt(world, position);

        if (part instanceof MightyFurnaceTileEntity) {

            MightyFurnaceTileEntity wallTile = (MightyFurnaceTileEntity)part;
            boolean assembled = wallTile.isConnected() && wallTile.getMultiblockController().isAssembled();
            BlockFacings facings = assembled ? wallTile.getOutwardsDir() : BlockFacings.ALL;

            //return facings.toBlockState(state);
            state = state.withProperty(FACES, facings.toProperty());


            // active icon hack

            if (wallTile.getPartPosition().isFace()) {

                MightyFurnaceController controller = (MightyFurnaceController)wallTile.getMultiblockController();
                boolean active = controller.isAssembled() && controller.isActive();

                if (active)
                    state = state.withProperty(FACES, PropertyBlockFacings.Opposite_EW);

            }

        }

        return state;
    }

    @Override
    protected BlockState createBlockState() {

        return new BlockState(this, new IProperty[] {FACES});
    }

    @Override
    protected void initBlock() {

        super.initBlock();
        //this.setDefaultState(BlockFacings.ALL.toBlockState(this.blockState.getBaseState()));
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACES, PropertyBlockFacings.All));
    }

    private final static PropertyEnum FACES ;

    static {

        ArrayList<PropertyBlockFacings> values = Lists.newArrayList();

        values.addAll(PropertyBlockFacings.ALL_AND_NONE);
        values.addAll(PropertyBlockFacings.FACES);
        values.addAll(PropertyBlockFacings.ANGLES);
        values.addAll(PropertyBlockFacings.CORNERS);
        values.add(PropertyBlockFacings.Opposite_EW);

        FACES = PropertyEnum.create("faces", PropertyBlockFacings.class, values);
    }


}
