package zero.mods.testmod18.common.potion;

import net.minecraft.util.ResourceLocation;
import zero.mods.testmod18.lib.References;
import zero.mods.zerocore.common.helpers.ModObjects;

public final class Potions {

    public static final PotionDrowning potionDrowning;

    public static final ResourceLocation POTIONS_TEXTURE;

    public static void Initialize() {
        // nothing to do here
    }

    private Potions() {
    }

    static {

        int id = ModObjects.expandVanillaPotionsRegistry(1);

        id = ModObjects.getNextCustomPotionId(id);
        potionDrowning = new PotionDrowning(id, new ResourceLocation("potionDrowning"), true, 0);

        POTIONS_TEXTURE = new ResourceLocation(References.MOD_ID, "textures/misc/potions.png");
    }
}
