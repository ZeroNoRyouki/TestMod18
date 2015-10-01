package zero.mods.testmod18.client.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import zero.mods.testmod18.EntryPoint;

public class TestFX2 extends EntityFX {

    public TestFX2(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {

        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);


        TextureMap textureMap = Minecraft.getMinecraft().getTextureMapBlocks();
        //String textureName = EntryPoint.getInstance().getModId() + ":textures/misc/1particles.png";
        //TextureAtlasSprite atlasSprite = textureMap.getAtlasSprite(textureName);

        TextureAtlasSprite atlasSprite = textureMap.registerSprite(TestFX2.s_particles);

        func_180435_a(atlasSprite);
        /*
        this.field_70545_g = state.func_177230_c().field_149763_I;
        this.field_70552_h = (this.field_70553_i = this.field_70551_j = 0.6F);
        this.field_70544_f /= 2.0F;
        */

        this.setSize(1,1);
        this.setRBGColorF(1,1,1);
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
    public int getFXLayer() {

        return 1;
    }

    protected static ResourceLocation s_particles = new ResourceLocation(EntryPoint.getInstance().getModId(), "textures/misc/1particles.png");
}
