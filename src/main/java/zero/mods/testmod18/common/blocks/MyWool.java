package zero.mods.testmod18.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.mods.zerocore.common.blocks.ModBlockWithState;

import java.util.List;
import java.util.Map;

/**
 * Created by marco on 22/06/2015.
 */
public class MyWool extends ModBlockWithState {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", MyWool.WoolType.class);


    public MyWool(String name) {

        super(name, Material.cloth);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, WoolType.TYPE1));
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

            WoolType[] types = WoolType.values();

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

        FMLLog.info("MyWool::getSubBlocksNamesMap called");

        for (int meta = 0; meta < 16; ++meta)
            map.put(meta, String.format("mywool_%d", meta + 1));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {

        WoolType type = (WoolType)state.getValue(TYPE);

        return type.ordinal();
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {

        return this.getDefaultState().withProperty(TYPE, WoolType.getTypeFromMetaData(meta));
    }

    @Override
    protected BlockState createBlockState() {

        return new BlockState(this, new IProperty[] {TYPE});
    }

    @Override
    protected void initBlock() {

        this.setCreativeTab(CreativeTabs.tabBlock);
        //this.setHardness(1.5F);
        //this.setResistance(10.0F);
        this.setStepSound(this.soundTypeCloth);
    }

    private ItemStack[] _subBlocks;


    public enum WoolType implements IStringSerializable {

        TYPE1("t1"),
        TYPE2("t2"),
        TYPE3("t3"),
        TYPE4("t4"),
        TYPE5("t5"),
        TYPE6("t6"),
        TYPE7("t7"),
        TYPE8("t8"),
        TYPE9("t9"),
        TYPE10("t10"),
        TYPE11("t11"),
        TYPE12("t12"),
        TYPE13("t13"),
        TYPE14("t14"),
        TYPE15("t15"),
        TYPE16("t16");

        WoolType(String name) {

            this._name = name;
        }

        @Override
        public String toString() {

            return this.getName();
        }

        @Override
        public String getName() {

            return this._name;
        }

        public static WoolType getTypeFromMetaData(int metaData) {

            if ((metaData < 0) || (metaData >= WoolType.s_lookup.length))
                metaData = 0;

            return WoolType.s_lookup[metaData];
        }


        private final String _name;

        private static final WoolType[] s_lookup = WoolType.values();

    }

}
