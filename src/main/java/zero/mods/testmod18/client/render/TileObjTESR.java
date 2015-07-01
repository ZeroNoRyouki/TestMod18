package zero.mods.testmod18.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import zero.mods.testmod18.lib.References;
import zero.mods.zerocore.client.model.ICustomModel;
import zero.mods.zerocore.client.model.Loader;

/**
 * Created by marco on 20/06/2015.
 */
public class TileObjTESR extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float p_180535_8_, int p_180535_9_) {

        //OpenGL stuff
        GL11.glPushMatrix();

        /* particolari di questo model */
        /*
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glTranslated(x + 0.2, y + 0.05, z + 0.8);
        GL11.glScalef(0.6F, 0.6F, 0.6F);
*/

        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
        //GL11.glTranslated(x, y, z);

        //Bind the texture and render the model

        this.bindTexture(this._model.getTexture());
        this._model.renderAll();

        //OpenGL stuff to put everything back
        GL11.glPopMatrix();

    }

    public TileObjTESR() {

        this._modelLocation = new ResourceLocation(References.MOD_ID, "models/bloodPillar.obj");
        this._textureLocation = new ResourceLocation(References.MOD_ID, "textures/bloodPillar.png");


        this._model = Loader.loadModel(this._modelLocation, this._textureLocation);

    }

    private ResourceLocation _modelLocation;
    private ResourceLocation _textureLocation;


    private ICustomModel _model;

}
