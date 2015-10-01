package zero.mods.testmod18.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import zero.mods.zerocore.common.blocks.ModBlockWithState;

public class BlockWithFacing extends ModBlockWithState {

    public BlockWithFacing(String name) {

        super(name, Material.rock, false);
    }

    @Override
    protected void initBlock() {

        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockWithFacing.FACING, EnumFacing.NORTH));

        this.setCreativeTab(CreativeTabs.tabBlock);
        //this.setHardness(1.5F);
        //this.setResistance(10.0F);
        this.setStepSound(this.soundTypeCloth);
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

        return this.getDefaultState().withProperty(BlockWithFacing.FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {

        return ((EnumFacing)state.getValue(BlockWithFacing.FACING)).getIndex();
    }

    protected BlockState createBlockState() {

        return new BlockState(this, new IProperty[] {BlockWithFacing.FACING});
    }



    // chiamato prima di posizionare il blocco nel mondo per determinarne il blockstate
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        FMLLog.info("BlockWithFacing::onBlockPlaced suggested facing: %s", facing);


        facing = (null != placer) ? placer.getHorizontalFacing().getOpposite() : EnumFacing.NORTH;

        return this.getDefaultState().withProperty(BlockWithFacing.FACING, facing);
    }

    // chiamato quando un blocco � posizionato via codice (world.setBlockState())
    public void onBlockAdded(World world, BlockPos position, IBlockState state) {

        FMLLog.info("BlockWithFacing::onBlockAdded %s %", position, state);

        EnumFacing newFacing = this.suggestDefaultFacing(world, position, (EnumFacing)state.getValue(BlockWithFacing.FACING));

        world.setBlockState(position, state.withProperty(BlockWithFacing.FACING, newFacing), 2);
    }

    // chiamato DOPO che il blocco � stato posizionato nel mondo
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {

        //worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        /*
        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityFurnace)
            {
                ((TileEntityFurnace)tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
        */

    }

    protected static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
}
