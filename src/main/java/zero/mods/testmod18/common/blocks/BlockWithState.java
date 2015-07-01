package zero.mods.testmod18.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import zero.mods.zerocore.common.blocks.ModBlockWithState;
import zero.mods.testmod18.common.items.Items;
import zero.mods.zerocore.common.helpers.CodeHelper;

/**
 * Created by marco on 26/05/2015.
 */
public class BlockWithState extends /* TestModBlock*/ ModBlockWithState {

    public static final PropertyBool LIT = PropertyBool.create("lit");
    public static final PropertyEnum COLOR = PropertyEnum.create("color", BlockWithState.ColorType.class);

    public BlockWithState(String name) {

        super(name, Material.rock);

        this.setDefaultState(this.blockState.getBaseState().withProperty(LIT, Boolean.FALSE).withProperty(COLOR, ColorType.RED));
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (CodeHelper.calledByServer(world)) {

            ItemStack heldItem = player.getHeldItem();

            if (null != heldItem && Items.testItem == heldItem.getItem())
                state = state.cycleProperty(COLOR);
            else
                state = state.cycleProperty(LIT);

            world.setBlockState(pos, state, 3);
        }

        return true;
    }


    @Override
    public String getBlockNameStateSuffix(ItemStack stack) {

        IBlockState state = this.getStateFromMeta(stack.getItemDamage());

        StringBuilder sb = new StringBuilder();

        ColorType color = (ColorType)state.getValue(COLOR);
        boolean lit = ((Boolean)state.getValue(LIT)).booleanValue();

        sb.append(color.toString());
        sb.append('_');
        sb.append(lit ? "lit" : "unlit");

        return sb.toString();
    }


    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {

        // bit 0 : LIT      1 = true, 0 = false     01  00
        // bit 1 : COLOR    1 = green, 0 = red      1x  0x

        int colorBit = ColorType.GREEN == state.getValue(COLOR) ? 2 : 0;
        int litBit = ((Boolean)state.getValue(LIT)).booleanValue() ? 1 : 0;

        return colorBit + litBit;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {

        boolean lit = (meta & 1) == 1;
        ColorType color = (meta & 2) == 2 ? ColorType.GREEN : ColorType.RED;

        return this.getDefaultState().withProperty(LIT, Boolean.valueOf(lit)).withProperty(COLOR, color);
    }


    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        // always lit the block during night

        //boolean obscured = !((World)worldIn).canBlockSeeSky(pos);
        boolean isNight = !((World)worldIn).provider.isDaytime();

        FMLLog.info("isNight = %s", Boolean.valueOf(isNight).toString());

        if (isNight)
            state = state.withProperty(LIT, true);

        return state;
    }

    @Override
    protected BlockState createBlockState() {

        return new BlockState(this, new IProperty[] {LIT, COLOR});
    }

    @Override
    protected void initBlock() {

        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(this.soundTypePiston);
    }


    public /*static*/ enum ColorType implements IStringSerializable {

        RED("red"),
        GREEN("green");

        ColorType(String name) {
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

        private final String _name;
    }

}
