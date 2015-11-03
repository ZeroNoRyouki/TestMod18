package zero.mods.testmod18.common.integration.thaumcraft;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.mods.zerocore.common.helpers.CodeHelper;

public class EntityVisBomb extends EntityThrowable {

    public EntityVisBomb(World world) {

        super(world);
    }

    public EntityVisBomb(World world, EntityLivingBase thrower) {

        super(world, thrower);
    }

    @SideOnly(Side.CLIENT)
    public EntityVisBomb(World world, double x, double y, double z) {

        super(world, x, y, z);
    }


    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    @Override
    protected void onImpact(MovingObjectPosition mop) {

        if (null == mop)
            return;

        if (null != mop.entityHit) {

            FMLLog.info("VisBomb: hit %s", mop.entityHit.toString());
        }

        if (CodeHelper.calledByLogicalServer(this.worldObj))
            this.worldObj.createExplosion(null, this.posX, this.posY, this.posZ, /* size */ 2.0f, /* flaming?*/ true);

        this.setDead();
    }
}
