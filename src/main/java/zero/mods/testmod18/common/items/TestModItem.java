package zero.mods.testmod18.common.items;

import net.minecraft.creativetab.CreativeTabs;
import zero.mods.zerocore.common.items.ModItem;

/**
 * Created by marco on 19/05/2015.
 */
public class TestModItem extends ModItem {

    public TestModItem(String name) {

        super(name);
    }

    @Override
    protected void initItem() {

        this.setCreativeTab(CreativeTabs.tabCombat);
        this.setMaxStackSize(64);
    }
}
