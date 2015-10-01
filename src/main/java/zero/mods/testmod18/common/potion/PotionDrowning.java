package zero.mods.testmod18.common.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.mods.zerocore.client.helpers.Render;

public class PotionDrowning extends Potion {

    public PotionDrowning(int potionID, ResourceLocation location, boolean isBadEffect, int potionColor) {

        super(potionID, location, isBadEffect, potionColor);

        this.setPotionName("potion.drowning");
        this.setIconIndex(3 ,1);
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {

        if (entity.getHealth() > 1.0F)
            entity.attackEntityFrom(DamageSource.drown, 1.0F);
    }


    @Override
    public boolean isReady(int p_76397_1_, int p_76397_2_) {

        return true;
        //return super.isReady(p_76397_1_, p_76397_2_);
    }


    @SideOnly(Side.CLIENT)
    @Override
    public int getStatusIconIndex() {

        Render.minecraftBindTexture(Potions.POTIONS_TEXTURE);
        return super.getStatusIconIndex();
    }
}
