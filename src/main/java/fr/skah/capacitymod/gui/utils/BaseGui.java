package fr.skah.capacitymod.gui.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

/*
 *  * @Created on jeudi/mai/2021 - 16:56
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

public abstract class BaseGui extends GuiScreen {

    private final int widthSize;
    private final int heightSize;

    private int x = 0;
    private int y = 0;

    private final ResourceLocation backgroundResourceLocation;

    public BaseGui(int widthSize, int heightSize, ResourceLocation backgroundResourceLocation) {
        this.widthSize = widthSize;
        this.heightSize = heightSize;
        this.backgroundResourceLocation = backgroundResourceLocation;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    @Override
    public void initGui() {
        x = (this.width + this.widthSize) / 2;
        y = (this.height + this.heightSize) / 2;

    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    @Override
    public void actionPerformed(GuiButton button) {
        if (button.id == 200)
            closeGui();
    }


    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawBackground(x - this.getWidthSize(), y - this.getHeightSize());
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    /**
     * Close current player opened gui
     */
    public void closeGui() {
        mc.displayGuiScreen(null);
        mc.setIngameFocus();
    }

    /**
     * Draws the background screen.
     */
    public void drawBackground(int x, int y) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(backgroundResourceLocation);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, getWidthSize(), getHeightSize() , getWidthSize(), getHeightSize());
    }

    public int getWidthSize() {
        return widthSize;
    }

    public int getHeightSize() {
        return heightSize;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
