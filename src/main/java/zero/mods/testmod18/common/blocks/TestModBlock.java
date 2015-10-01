package zero.mods.testmod18.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import zero.mods.zerocore.common.blocks.ModBlock;

/**
 * Created by marco on 23/05/2015.
 */
public class TestModBlock extends ModBlock {

    public TestModBlock(String name, Material material) {

        super(name, material);
    }

    public TestModBlock(String name) {

        super(name, Material.rock);
    }

    /*
    @Override
    public int getRenderType() {
        return -1; //super.getRenderType();
    }
*/

    @Override
    protected void initBlock() {

        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(this.soundTypePiston);
    }
}
