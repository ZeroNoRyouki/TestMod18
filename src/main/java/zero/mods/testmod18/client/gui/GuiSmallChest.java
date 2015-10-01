package zero.mods.testmod18.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import zero.mods.testmod18.common.blocks.ContainerSmallChest;
import zero.mods.testmod18.common.blocks.tile.TileSmallChest;
import zero.mods.testmod18.lib.References;
import zero.mods.zerocore.client.helpers.Render;

import java.awt.*;


public class GuiSmallChest extends GuiContainer {

    public GuiSmallChest(InventoryPlayer playerInventory, TileSmallChest tileEntity) {

        super(new ContainerSmallChest(playerInventory, tileEntity));

        this._tileEntity = tileEntity;

        // Set the width and height of the gui.  Should match the size of the texture!
        this.xSize = 176;
        this.ySize = 166;

    }

    // draw the background for the GUI - rendered first
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {

        // Bind the image texture of our custom container
        Render.minecraftBindTexture(GuiSmallChest.s_texture);

        // Draw the image
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    // draw the foreground for the GUI - rendered after the slots, but before the dragged items and tooltips
    // renders relative to the top left corner of the background
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        final int LABEL_XPOS = 5;
        final int LABEL_YPOS = 5;

        this.fontRendererObj.drawString(this._tileEntity.getDisplayName().getUnformattedText(), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());
    }



    private TileSmallChest _tileEntity;
    private static final ResourceLocation s_texture = new ResourceLocation(References.MOD_ID, "textures/gui/container/smallchest.png");

}
