package zero.mods.testmod18.client.gui;


import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.mods.testmod18.common.blocks.ContainerDirtChest90000;
import zero.mods.testmod18.common.blocks.tile.TileDirtChest90000;
import zero.mods.testmod18.lib.References;
import zero.mods.zerocore.client.helpers.Render;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiDirtChest90000 extends GuiContainer {

    public GuiDirtChest90000(InventoryPlayer playerInventory, TileDirtChest90000 tileEntity) {

        super(new ContainerDirtChest90000(playerInventory, tileEntity));

        this._tileEntity = tileEntity;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {

        // Bind the image texture of our custom container
        Render.minecraftBindTexture(GuiDirtChest90000.s_texture);

        // Draw the image
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    // draw the foreground for the GUI - rendered after the slots, but before the dragged items and tooltips
    // renders relative to the top left corner of the background
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        final int LABEL_XPOS = 5;
        final int LABEL_YPOS = 5;

        this.fontRendererObj.drawString(this._tileEntity.getDisplayName().getUnformattedText(), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());
    }



    private TileDirtChest90000 _tileEntity;
    private static final ResourceLocation s_texture = new ResourceLocation(References.MOD_ID, "textures/gui/container/dirtchest90000.png");

}
