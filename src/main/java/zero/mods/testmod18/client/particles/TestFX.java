package zero.mods.testmod18.client.particles;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.mods.testmod18.EntryPoint;
import zero.mods.zerocore.client.helpers.Render;

@SideOnly(Side.CLIENT)
public class TestFX extends EntityFX {

    public TestFX(World world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, float scale) {

        super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

        FMLLog.info("TestFX ctor %f %f %f", xCoordIn, yCoordIn, zCoordIn);

        // pixie trail texture is row from position 0,1 to position 8,1
        this.particleTextureIndexX = 0;
        this.particleTextureIndexY = 0;

        this.setRBGColorF(1, 0, 0);

        /*
        this.motionX *= 0.10000000149011612D;
        this.motionY *= 0.10000000149011612D;
        this.motionZ *= 0.10000000149011612D;
        this.motionX += xSpeedIn;
        this.motionY += ySpeedIn;
        this.motionZ += zSpeedIn;
        */
        /*
        this.particleScale *= 0.75F;
        this.particleScale *= scale;
        this.particleMaxAge = (int)((8.0D / (Math.random() * 0.8D + 0.2D)) * 8);
        this.particleMaxAge = (int)((float)this.particleMaxAge * scale);
        this.particleAge = (particleMaxAge / 2) + (int)((particleMaxAge / 2) * world.rand.nextInt(7));
        */
        this.particleAlpha = 1.0F;
        this.noClip = false;

        this.setSize(1,1);
    }

    public void spawn() {

        /*
        WorldClient world = FMLClientHandler.instance().getClient().theWorld;

        if (null != world)
            world.spawnEntityInWorld(this);
        */
        FMLClientHandler.instance().getClient().effectRenderer.addEffect(this);
    }

    @Override
    public void func_180434_a(WorldRenderer renderer, Entity entity, float partialTicks, float rotX, float rotXZ, float rotZ, float rotYZ, float rotXY) {
                           // renderer, entity, partialTicks, rotX, rotXZ, rotZ, rotYZ, rotXY

        FMLLog.info("TestFX render? %f %f %f %f %f %f", partialTicks, rotX, rotXZ, rotZ, rotYZ, rotXY);

        ResourceLocation r = new ResourceLocation(EntryPoint.getInstance().getModId(), "textures/misc/particles.png");




        // EffectRenderer will by default bind the vanilla particles texture, override with our own
        Render.minecraftBindTexture(r);

        float scaleMultiplier = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge * 32.0F;
        scaleMultiplier = MathHelper.clamp_float(scaleMultiplier, 0.0F, 1.0F);
        this.particleScale = this.particleScale * scaleMultiplier;

        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 1);

        super.func_180434_a(renderer, entity, partialTicks, rotX, rotXZ, rotZ, rotYZ, rotXY);

        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
    }

    @Override
    public int getFXLayer() {

        return 3;
    }

    /**
     * Public method to set private field particleTextureIndex.
     */
    @Override
    public void setParticleTextureIndex(int index) {

        this.particleTextureIndexX = index % 16;
        this.particleTextureIndexY = index / 16;
    }


    @Override
    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        if (particleAge++ >= particleMaxAge)
        {
            this.setDead();
        }

        this.particleTextureIndexX = 7 - particleAge * 8 / particleMaxAge;
        this.moveEntity(motionX, motionY, motionZ);

        if (posY == prevPosY)
        {
            motionX *= 1.1D;
            motionZ *= 1.1D;
        }

        motionX *= 0.9599999785423279D;
        motionY *= 0.9599999785423279D;
        motionZ *= 0.9599999785423279D;

        if (onGround)
        {
            motionX *= 0.699999988079071D;
            motionZ *= 0.699999988079071D;
        }
    }

    protected static ResourceLocation s_particles = new ResourceLocation(EntryPoint.getInstance().getModId(), "textures/misc/particles.png");
}
