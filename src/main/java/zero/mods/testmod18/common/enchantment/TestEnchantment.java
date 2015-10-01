package zero.mods.testmod18.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class TestEnchantment extends Enchantment {

    public TestEnchantment(int enchID) {

        super(enchID, new ResourceLocation("testEnchantment"), 2, EnumEnchantmentType.WEAPON);
        this.setName("test_enchantment");
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    @Override
    public int getMaxLevel() {

        return 3;
    }

    /**
     * Calculates the additional damage that will be dealt by an item with this enchantment. This alternative to
     * calcModifierDamage is sensitive to the targets EnumCreatureAttribute.
     *
     * @param level The level of this specific enchantment.
     * @param creatureType The EnumCreatureAttribute which represents the target entity. This can be used to have an
     * effect only apply to a specific group of creatures such as Undead or Arthropods.
     */
    public float calcDamageByCreature(int level, EnumCreatureAttribute creatureType) {

        return 5.0F * level;
    }



}
