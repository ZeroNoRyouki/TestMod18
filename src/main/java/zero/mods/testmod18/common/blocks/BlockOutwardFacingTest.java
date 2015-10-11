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
import net.minecraftforge.client.model.ModelLoader;
import zero.mods.zerocore.common.blocks.ModBlockWithState;

/**
 * Created by marco on 07/10/2015.
 */
public class BlockOutwardFacingTest extends ModBlockWithState {
    /*
    public static PropertyEnum FACING_UP = PropertyEnum.create("upface", FaceConnection.class);
    public static PropertyEnum FACING_DOWN = PropertyEnum.create("downface", FaceConnection.class);
    public static PropertyEnum FACING_EAST = PropertyEnum.create("eastface", FaceConnection.class);
    public static PropertyEnum FACING_WEST = PropertyEnum.create("westface", FaceConnection.class);
    public static PropertyEnum FACING_SUD = PropertyEnum.create("southface", FaceConnection.class);
    public static PropertyEnum FACING_NORTH = PropertyEnum.create("northface", FaceConnection.class);
    */

    public static PropertyBool FACING_UP = PropertyBool.create("upface");
    public static PropertyBool FACING_DOWN = PropertyBool.create("downface");
    public static PropertyBool FACING_EAST = PropertyBool.create("eastface");
    public static PropertyBool FACING_WEST = PropertyBool.create("westface");
    public static PropertyBool FACING_SOUTH = PropertyBool.create("southface");
    public static PropertyBool FACING_NORTH = PropertyBool.create("northface");


    //public static PropertyEnum BLOCK_TYPE = PropertyEnum.create("type", BlockType.class);


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
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        boolean north, south, west, east, down, up;

        north = worldIn.isAirBlock(pos.north());
        south = worldIn.isAirBlock(pos.south());
        west = worldIn.isAirBlock(pos.west());
        east = worldIn.isAirBlock(pos.east());
        down = worldIn.isAirBlock(pos.down());
        up = worldIn.isAirBlock(pos.up());

        return state.withProperty(FACING_NORTH, north).withProperty(FACING_SOUTH, south).withProperty(FACING_WEST, west).
                withProperty(FACING_EAST, east).withProperty(FACING_DOWN, down).withProperty(FACING_UP, up);


        //return super.getActualState(state, worldIn, pos);
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
                .withProperty(FACING_WEST, false).withProperty(FACING_NORTH, false).withProperty(FACING_SOUTH, false)/*.withProperty(BLOCK_TYPE, BlockType.Wall)*/);
    }



    @Override
    protected BlockState createBlockState() {

        return new BlockState(this, new IProperty[] {FACING_UP, FACING_DOWN, FACING_EAST, FACING_WEST, FACING_NORTH, FACING_SOUTH/*, BLOCK_TYPE*/});
    }

    public enum FaceConnection implements IStringSerializable {

        None("none"),
        All("all"),
        Down("d"),
        Left("l"),
        Right("r"),
        Up("u"),
        DownLeft("dl"),
        LeftUp("lu"),
        RightDown("rd"),
        RightLeft("rl"),
        UpDown("ud"),
        UpRight("ur"),
        DownLeftUp("dlu"),
        LeftUpRight("lur"),
        RightDownLeft("rdl"),
        UpRightDown("urd");

        FaceConnection(String name) {

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
