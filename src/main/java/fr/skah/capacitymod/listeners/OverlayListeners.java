package fr.skah.capacitymod.listeners;

/*
 *  * @Created on samedi/mai/2021 - 18:02
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import fr.skah.capacitymod.CapacityMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class OverlayListeners {

    public static float LEVEL_UP = 0;
    public static int PLAYER_LEVEL = 0;

    public static float EXP_UP = 0;
    public static int EXP = 0;

    public static final ResourceLocation LEVEL_UP_OVERLAY = new ResourceLocation(CapacityMod.MODID, "textures/gui/level_up_banner.png");


    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (LEVEL_UP > 0 || EXP_UP > 0) {
            Minecraft minecraft = FMLClientHandler.instance().getClient();
            if (minecraft.currentScreen == null && event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                ScaledResolution scaledresolution = event.getResolution();

                int sizeX = 378 / scaledresolution.getScaleFactor();
                int sizeY = 110 / scaledresolution.getScaleFactor();

                int dividedSizeX = sizeX / 2;

                int x = scaledresolution.getScaledWidth() / 2 - dividedSizeX;
                int y = scaledresolution.getScaledHeight() / 20;
                GL11.glColor4f(1, 1, 1, 1);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(770, 771);

                if (LEVEL_UP > 0) {
                    minecraft.getTextureManager().bindTexture(LEVEL_UP_OVERLAY);
                    Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, sizeX, sizeY, sizeX, sizeY);
                    String text = "Bravo, vous êtes passez Niveau " + PLAYER_LEVEL;
                    minecraft.fontRenderer.drawString(text, (scaledresolution.getScaledWidth() / 2 - minecraft.fontRenderer.getStringWidth(text) / 2), y + 70 / scaledresolution.getScaleFactor(), Color.GRAY.getRGB(), true);
                    LEVEL_UP = LEVEL_UP - event.getPartialTicks();
                    return;
                }
                if (EXP_UP > 0) {
                    String text = "Vous avez gagné +" + EXP + "XP";
                    minecraft.fontRenderer.drawString(text, scaledresolution.getScaledWidth() - minecraft.fontRenderer.getStringWidth(text) - 1, y / scaledresolution.getScaleFactor(), Color.GRAY.getRGB(), true);
                    EXP_UP = EXP_UP - event.getPartialTicks();
                }
            }
        }
    }

}
