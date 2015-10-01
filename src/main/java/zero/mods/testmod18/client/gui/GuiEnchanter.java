package zero.mods.testmod18.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.mods.testmod18.common.blocks.ContainerEnchanter;
import zero.mods.testmod18.common.blocks.tile.TileEnchanter;
import zero.mods.testmod18.lib.References;
import zero.mods.zerocore.client.helpers.Render;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiEnchanter extends GuiContainer {

    public GuiEnchanter(InventoryPlayer playerInventory, TileEnchanter tileEntity) {

        super(new ContainerEnchanter(playerInventory, tileEntity));

        this._tileEntity = tileEntity;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {

        // Bind the image texture of our custom container
        Render.minecraftBindTexture(GuiEnchanter.s_texture);

        // Draw the image
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);


        // draw progress bar

        if (this._tileEntity.isEnchanting()) {

            //int progressBarWidth = this.computeProgressBarWidth(22);
            int progressBarWidth = Render.computeGuiProgressBarWidth(22,
                    this._tileEntity.getMaxProcessingTime(), this._tileEntity.getCurrentProcessingTime());

            int xProgress = (this.width - this.xSize) / 2 + 79;
            int yProgress = (this.height - this.ySize) / 2 + 34;
            int textureY = 16 * this._tileEntity.getEnchantType().ordinal();

            this.drawTexturedModalRect(xProgress, yProgress, 176, textureY, progressBarWidth, 16);
        }
    }

    // draw the foreground for the GUI - rendered after the slots, but before the dragged items and tooltips
    // renders relative to the top left corner of the background
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        final int LABEL_XPOS = 5;
        final int LABEL_YPOS = 5;

        this.fontRendererObj.drawString(this._tileEntity.getDisplayName().getUnformattedText(), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());
    }

    protected int computeProgressBarWidth(int maxWidth) {

        int currentProcessingTime = this._tileEntity.getCurrentProcessingTime();
        int maxProcessingTime = this._tileEntity.getMaxProcessingTime();

        return maxProcessingTime != 0 && currentProcessingTime != 0 ? currentProcessingTime * maxWidth / maxProcessingTime : 0;
    }

    private TileEnchanter _tileEntity;
    private static final ResourceLocation s_texture = new ResourceLocation(References.MOD_ID, "textures/gui/container/enchanter.png");
}
