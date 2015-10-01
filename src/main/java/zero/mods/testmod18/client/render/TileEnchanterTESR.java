package zero.mods.testmod18.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import zero.mods.testmod18.lib.References;
import zero.mods.zerocore.client.model.ICustomModel;
import zero.mods.zerocore.client.model.Loader;

@SideOnly(Side.CLIENT)
public class TileEnchanterTESR extends TileEntitySpecialRenderer {

    public TileEnchanterTESR() {

        this._modelLocation = new ResourceLocation(References.MOD_ID, "models/bloodPillar.obj");
        this._textureLocation = new ResourceLocation(References.MOD_ID, "textures/bloodPillar.png");

        this._model = Loader.loadModel(this._modelLocation, this._textureLocation);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float p_180535_8_, int p_180535_9_) {

        // save OpenGL data
        GL11.glPushMatrix();

        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);


        //Bind the texture and render the model

        this.bindTexture(this._model.getTexture());
        this._model.renderAll();

        // restore OpenGL data
        GL11.glPopMatrix();
    }



    private ResourceLocation _modelLocation;
    private ResourceLocation _textureLocation;


    private ICustomModel _model;

}
