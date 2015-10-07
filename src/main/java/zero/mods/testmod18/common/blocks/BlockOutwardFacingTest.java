package zero.mods.testmod18.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import zero.mods.zerocore.common.blocks.ModBlockWithState;

/**
 * Created by marco on 07/10/2015.
 */
public class BlockOutwardFacingTest extends ModBlockWithState {

    public static PropertyBool FACING_UP = PropertyBool.create("up");
    public static PropertyBool FACING_DOWN = PropertyBool.create("down");
    public static PropertyBool FACING_EAST = PropertyBool.create("east");
    public static PropertyBool FACING_WEST = PropertyBool.create("west");
    public static PropertyBool FACING_NORTH = PropertyBool.create("north");
    public static PropertyBool FACING_SUD = PropertyBool.create("sud");
    public static PropertyEnum BLOCK_TYPE = PropertyEnum.create("type", BlockType.class);


    public BlockOutwardFacingTest(String name) {

        super(name, Material.rock, false);
    }

    @Override
    public int getMetaFromState(IBlockState state) {

        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {

        return super.getStateFromMeta(meta);
    }

    @Override
    public String getBlockNameStateSuffix(ItemStack stack) {
        return null;
    }

    @Override
    protected void initBlock() {

        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(this.soundTypePiston);

        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING_DOWN, false).withProperty(FACING_UP, false).withProperty(FACING_EAST, false)
                .withProperty(FACING_WEST, false).withProperty(FACING_NORTH, false).withProperty(FACING_SUD, false).withProperty(BLOCK_TYPE, BlockType.Wall));
    }


    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        return super.getActualState(state, worldIn, pos);
    }


    @Override
    protected BlockState createBlockState() {

        return new BlockState(this, new IProperty[] {FACING_UP, FACING_DOWN, FACING_EAST, FACING_WEST, FACING_NORTH, FACING_SUD});
    }

    public enum BlockType implements IStringSerializable {

        Wall("wall"),
        Power("power"),
        Input("input"),
        Output("output");

        BlockType(String name) {

            this._name = name;
        }

        @Override
        public String toString() {

            return this._name;
        }

        @Override
        public String getName() {

            return this._name;
        }


        private String _name;
    }
}
