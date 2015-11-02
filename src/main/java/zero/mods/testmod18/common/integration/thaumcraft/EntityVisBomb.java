package zero.mods.testmod18.common.integration.thaumcraft;

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.FMLLog;

public class EntityVisBomb extends EntityThrowable {



    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    @Override
    protected void onImpact(MovingObjectPosition mop) {

        if (null != mop.entityHit) {

            FMLLog.info("VisBomb: hit %s", mop.entityHit.toString());
        }


    }
}
