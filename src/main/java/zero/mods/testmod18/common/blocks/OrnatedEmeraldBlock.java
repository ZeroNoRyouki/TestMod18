package zero.mods.testmod18.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

/**
 * Created by marco on 03/06/2015.
 */
public class OrnatedEmeraldBlock extends TestModBlock {

    public static final PropertyBool CONNECTED_DOWN = PropertyBool.create("down");
    public static final PropertyBool CONNECTED_EAST = PropertyBool.create("east");
    public static final PropertyBool CONNECTED_NORTH = PropertyBool.create("north");
    public static final PropertyBool CONNECTED_SOUTH = PropertyBool.create("south");
    public static final PropertyBool CONNECTED_UP = PropertyBool.create("up");
    public static final PropertyBool CONNECTED_WEST = PropertyBool.create("west");


    public OrnatedEmeraldBlock(String name) {

        super(name, Material.rock);

        this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECTED_DOWN, Boolean.FALSE).withProperty(CONNECTED_EAST, Boolean.FALSE)
                        .withProperty(CONNECTED_NORTH, Boolean.FALSE).withProperty(CONNECTED_SOUTH, Boolean.FALSE)
                        .withProperty(CONNECTED_UP, Boolean.FALSE).withProperty(CONNECTED_WEST, Boolean.FALSE));

    }


    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos position) {

        state = state
                .withProperty(CONNECTED_DOWN, this.isAdjacentBlockOfMyType(world, position, EnumFacing.DOWN))
                .withProperty(CONNECTED_EAST, this.isAdjacentBlockOfMyType(world, position, EnumFacing.EAST))
                .withProperty(CONNECTED_NORTH, this.isAdjacentBlockOfMyType(world, position, EnumFacing.NORTH))
                .withProperty(CONNECTED_SOUTH, this.isAdjacentBlockOfMyType(world, position, EnumFacing.SOUTH))
                .withProperty(CONNECTED_UP, this.isAdjacentBlockOfMyType(world, position, EnumFacing.UP))
                .withProperty(CONNECTED_WEST, this.isAdjacentBlockOfMyType(world, position, EnumFacing.WEST));

        //this.debugDumpBlockState(state, position);
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {

        return 0;
    }

    @Override
    protected BlockState createBlockState() {

        return new BlockState(this, new IProperty[] {CONNECTED_DOWN, CONNECTED_UP, CONNECTED_NORTH, CONNECTED_SOUTH, CONNECTED_WEST, CONNECTED_EAST});
    }

}
