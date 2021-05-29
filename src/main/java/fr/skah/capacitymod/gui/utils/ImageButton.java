package fr.skah.capacitymod.gui.utils;

/*
 *  * @Created on jeudi/mai/2021 - 16:56
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class ImageButton extends GuiButton {

    private final ResourceLocation buttonResourceLocation;
    private final ResourceLocation buttonHoverResourceLocation;

    public ImageButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, ResourceLocation buttonResourceLocation, ResourceLocation buttonHoverResourceLocation) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
        this.buttonResourceLocation = buttonResourceLocation;
        this.buttonHoverResourceLocation = buttonHoverResourceLocation;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {

        if (this.visible) {
            boolean mouseHover = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            if (!mouseHover) mc.getTextureManager().bindTexture(buttonResourceLocation);
            else mc.getTextureManager().bindTexture(buttonHoverResourceLocation);


            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            drawScaledCustomSizeModalRect(this.x, this.y, 0, 0, this.width, this.height, this.width, this.height, this.width, this.height);
            drawCenteredString(mc.fontRenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, Color.WHITE.getRGB());

        }
    }
}
