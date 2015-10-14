package zero.mods.testmod18.common.multiblock;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zero.mods.testmod18.common.multiblock.tile.MightyFurnaceTileEntity;
import zero.mods.zerocore.common.blocks.ModBlock;
import zero.mods.zerocore.common.lib.BlockFacings;
import zero.mods.zerocore.common.multiblock.IMultiblockPart;
import zero.mods.zerocore.common.multiblock.rectangular.PartPosition;

/**
 * Created by marco on 12/10/2015.
 */
public class MightyFurnaceBlockPort extends MightyFurnaceBlockBase {

    public static final PropertyBool ASSEMBLED = PropertyBool.create("assembled");


    public MightyFurnaceBlockPort(String name, MightyFurnaceBlockType portType) {

        super(name, portType);

        if (MightyFurnaceBlockType.Wall == portType)
            throw new IllegalArgumentException("Invalid port type");
    }

    @Override
    public String getBlockNameStateSuffix(ItemStack stack) {
        return null;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {

        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (EnumFacing.Axis.Y == enumfacing.getAxis())
            enumfacing = EnumFacing.NORTH;

        return this.getDefaultState().withProperty(ModBlock.HFACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {

        return ((EnumFacing)state.getValue(ModBlock.HFACING)).getIndex();
    }


    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos position) {

        IMultiblockPart part = this.getMultiblockPartAt(world, position);

        if (part instanceof MightyFurnaceTileEntity) {

            MightyFurnaceTileEntity wallTile = (MightyFurnaceTileEntity)part;
            boolean assembled = wallTile.isConnected() && wallTile.getMultiblockController().isAssembled();

            state = state.withProperty(ASSEMBLED, assembled);

            if (assembled) {

                switch (wallTile.getPartPosition()) {

                    case NorthFace:
                        state = state.withProperty(ModBlock.HFACING, EnumFacing.NORTH);
                        break;

                    case SouthFace:
                        state = state.withProperty(ModBlock.HFACING, EnumFacing.SOUTH);
                        break;

                    case WestFace:
                        state = state.withProperty(ModBlock.HFACING, EnumFacing.WEST);
                        break;

                    case EastFace:
                        state = state.withProperty(ModBlock.HFACING, EnumFacing.EAST);
                        break;
                }
            }
        }

        return state;
    }

    /***
     * Called before a block is placed in the world to generate the block state for the block
     */
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {

        facing = (null != placer) ? placer.getHorizontalFacing().getOpposite() : EnumFacing.NORTH;

        return this.getDefaultState().withProperty(ModBlock.HFACING, facing);
    }

    /**
     * Called when a block is placed in the world by code (world.setBlockState())
     * */
    public void onBlockAdded(World world, BlockPos position, IBlockState state) {

        EnumFacing newFacing = this.suggestDefaultFacing(world, position, (EnumFacing)state.getValue(ModBlock.HFACING));

        world.setBlockState(position, state.withProperty(ModBlock.HFACING, newFacing), 2);
    }



    @Override
    protected BlockState createBlockState() {

        return new BlockState(this, new IProperty[] {ModBlock.HFACING, ASSEMBLED});
    }

    @Override
    protected void initBlock() {

        super.initBlock();
        this.setDefaultState(this.blockState.getBaseState().withProperty(ModBlock.HFACING, EnumFacing.NORTH).withProperty(ASSEMBLED, false));
    }




}
