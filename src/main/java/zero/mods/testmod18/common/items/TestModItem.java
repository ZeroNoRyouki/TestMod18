package zero.mods.testmod18.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import zero.mods.testmod18.common.potion.Potions;
import zero.mods.zerocore.common.helpers.CodeHelper;
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

    /*
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target) {

        if ((null == stack) || (null == playerIn) || (null == target) || !CodeHelper.calledByLogicalServer(playerIn.worldObj))
            return false;

        target.addPotionEffect(new PotionEffect(Potions.potionDrowning.getId(), 100, 0, false, true));

        return true;
    }*/

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {

        if ((null != target) || !CodeHelper.calledByLogicalServer(target.worldObj)) {

            if (stack.getItem() == Items.simpleItem)
                target = attacker;

            target.addPotionEffect(new PotionEffect(Potions.potionDrowning.getId(), 100, 2, true, true));
            //target.addPotionEffect(new PotionEffect(Potion.poison.getId(), 100, 2, true, true));
            return true;
        }

        return super.hitEntity(stack, target, attacker);
    }
}
