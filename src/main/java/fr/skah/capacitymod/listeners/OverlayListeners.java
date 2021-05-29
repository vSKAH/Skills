package fr.skah.capacitymod.listeners;

/*
 *  * @Created on samedi/mai/2021 - 18:02
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import fr.skah.capacitymod.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class OverlayListeners {

    public static float TIMER = 0;
    public static int LEVEL = 0;

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (TIMER > 0 && event.getType().equals(RenderGameOverlayEvent.ElementType.ALL)) {
            Minecraft minecraft = FMLClientHandler.instance().getClient();
            if (minecraft.currentScreen == null) {
                ScaledResolution scaledresolution = event.getResolution();

                int sizeX = 378 / scaledresolution.getScaleFactor();
                int sizeY = 110 / scaledresolution.getScaleFactor();

                int dividedSizeX = sizeX / 2;

                int x = scaledresolution.getScaledWidth() / 2 - dividedSizeX;
                int y = scaledresolution.getScaledHeight() / 20;

                GL11.glColor4f(1, 1, 1, 1);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(770, 771);
                minecraft.getTextureManager().bindTexture(ClientProxy.LEVEL_UP_OVERLAY);
                Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, sizeX, sizeY, sizeX, sizeY);

                String text = "Bravo, vous êtes passez Niveau " + LEVEL;

                minecraft.fontRenderer.drawString(text, (scaledresolution.getScaledWidth() / 2 - minecraft.fontRenderer.getStringWidth(text) / 2), y + 70 / scaledresolution.getScaleFactor(), Color.GRAY.getRGB(), true);
                TIMER = TIMER - event.getPartialTicks();
            }
        }
    }

}
