package zero.mods.testmod18.common.blocks.RF;

import cofh.api.energy.IEnergyConnection;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import zero.mods.zerocore.common.blocks.ModBlockWithState;

public abstract class BlockRF extends ModBlockWithState {

    public static final PropertyBool CONNECTED_UP = PropertyBool.create("connectedUp");
    public static final PropertyBool CONNECTED_DOWN = PropertyBool.create("connectedDown");
    public static final PropertyBool CONNECTED_NORTH = PropertyBool.create("connectedNorth");
    public static final PropertyBool CONNECTED_SOUTH = PropertyBool.create("connectedSouth");
    public static final PropertyBool CONNECTED_EAST = PropertyBool.create("connectedEast");
    public static final PropertyBool CONNECTED_WEST = PropertyBool.create("connectedWest");


    public BlockRF(String name) {

        super(name, Material.iron, false);
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
    }

    @Override
    protected BlockState createBlockState() {

        return new BlockState(this, new IProperty[] {CONNECTED_UP, CONNECTED_DOWN, CONNECTED_NORTH,
                CONNECTED_SOUTH, CONNECTED_EAST, CONNECTED_WEST});
    }
}
