package fr.skah.capacitymod.gui;

/*
 *  * @Created on jeudi/mai/2021 - 17:00
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import fr.skah.capacitymod.CapacityMod;
import fr.skah.capacitymod.capability.IPlayerSkills;
import fr.skah.capacitymod.capability.PlayerSkillsStorage;
import fr.skah.capacitymod.gui.utils.BaseGui;
import fr.skah.capacitymod.gui.utils.ImageButton;
import fr.skah.capacitymod.network.RequestSynchronisePacket;
import fr.skah.capacitymod.network.RequestUpgrade;
import fr.skah.capacitymod.utils.Level;
import fr.skah.capacitymod.utils.Skills;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class CapacityGui extends BaseGui {

    private static final ResourceLocation PROGRESS_BAR = new ResourceLocation(CapacityMod.MODID, "textures/gui/progress_bar.png");
    private static final ResourceLocation PROGRESS_BAR_FILLED = new ResourceLocation(CapacityMod.MODID, "textures/gui/progress_bar_filled.png");

    private static final ResourceLocation ADD_BUTON = new ResourceLocation(CapacityMod.MODID, "textures/gui/buttons/add.png");
    private static final ResourceLocation ADD_HOVER_BUTON = new ResourceLocation(CapacityMod.MODID, "textures/gui/buttons/add_hover.png");
    private IPlayerSkills playerSkills;

    public CapacityGui() {
        super(200, 380, new ResourceLocation(CapacityMod.MODID, "textures/gui/background.png"));
    }

    @Override
    public void initGui() {
        super.initGui();
        playerSkills = mc.player.getCapability(PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY, null);
        buttonList.add(new ImageButton(150, getX() - 37, getY() - getHeightSize() + 147, 15, 15, "", ADD_BUTON, ADD_HOVER_BUTON));
        buttonList.add(new ImageButton(151, getX() - 37, getY() - getHeightSize() + 180, 15, 15, "", ADD_BUTON, ADD_HOVER_BUTON));
        buttonList.add(new ImageButton(152, getX() - 37, getY() - getHeightSize() + 212, 15, 15, "", ADD_BUTON, ADD_HOVER_BUTON));
        buttonList.add(new ImageButton(153, getX() - 37, getY() - getHeightSize() + 246, 15, 15, "", ADD_BUTON, ADD_HOVER_BUTON));
        buttonList.add(new ImageButton(154, getX() - 37, getY() - getHeightSize() + 279, 15, 15, "", ADD_BUTON, ADD_HOVER_BUTON));
        buttonList.add(new ImageButton(155, getX() - 37, getY() - getHeightSize() + 311, 15, 15, "", ADD_BUTON, ADD_HOVER_BUTON));
    }

    @Override
    public void updateScreen() {
        playerSkills = mc.player.getCapability(PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY, null);
        super.updateScreen();
    }

    @Override
    public void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
        switch (button.id) {
            case 150:
                CapacityMod.NETWORK_WRAPPER.sendToServer(new RequestUpgrade(Skills.VITALITY.name()));
                CapacityMod.NETWORK_WRAPPER.sendToServer(new RequestSynchronisePacket());
                break;
            case 151:
                CapacityMod.NETWORK_WRAPPER.sendToServer(new RequestUpgrade(Skills.SAGE.name()));
                CapacityMod.NETWORK_WRAPPER.sendToServer(new RequestSynchronisePacket());
                break;
            case 152:
                CapacityMod.NETWORK_WRAPPER.sendToServer(new RequestUpgrade(Skills.STRENGHT.name()));
                CapacityMod.NETWORK_WRAPPER.sendToServer(new RequestSynchronisePacket());
                break;
            case 153:
                CapacityMod.NETWORK_WRAPPER.sendToServer(new RequestUpgrade(Skills.INTELLIGENCE.name()));
                CapacityMod.NETWORK_WRAPPER.sendToServer(new RequestSynchronisePacket());
                break;
            case 154:
                CapacityMod.NETWORK_WRAPPER.sendToServer(new RequestUpgrade(Skills.LUCK.name()));
                CapacityMod.NETWORK_WRAPPER.sendToServer(new RequestSynchronisePacket());
                break;
            case 155:
                CapacityMod.NETWORK_WRAPPER.sendToServer(new RequestUpgrade(Skills.AIR.name()));
                CapacityMod.NETWORK_WRAPPER.sendToServer(new RequestSynchronisePacket());
                break;

        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        drawEntity((getX() - getWidthSize()) + 58, getY() - getHeightSize() + 88, 27, (getX() / 2 + 160) - mouseX, (getY() / 2 - 100) - mouseY, mc.player);
        drawProgressBar(playerSkills.getExperience(), Level.levelToExp(playerSkills.getGlobalLevel()));

        fontRenderer.drawString(mc.player.getName(), getX() - 78, getY() - getHeightSize() + 50, Color.DARK_GRAY.getRGB());
        fontRenderer.drawString(String.valueOf(playerSkills.getGlobalLevel()), getX() - 36, getY() - getHeightSize() + 68, Color.GRAY.getRGB());

        fontRenderer.drawString(String.valueOf(playerSkills.getVitalityLevel()), getX() - 47, getY() - getHeightSize() + 152, Color.GRAY.getRGB());
        fontRenderer.drawString(String.valueOf(playerSkills.getSageLevel()), getX() - 47, getY() - getHeightSize() + 185, Color.GRAY.getRGB());
        fontRenderer.drawString(String.valueOf(playerSkills.getStrengthLevel()), getX() - 47, getY() - getHeightSize() + 216, Color.GRAY.getRGB());
        fontRenderer.drawString(String.valueOf(playerSkills.getIntelligenceLevel()), getX() - 47, getY() - getHeightSize() + 250, Color.GRAY.getRGB());
        fontRenderer.drawString(String.valueOf(playerSkills.getLuckLevel()), getX() - 47, getY() - getHeightSize() + 283, Color.GRAY.getRGB());
        fontRenderer.drawString(String.valueOf(playerSkills.getAirLevel()), getX() - 47, getY() - getHeightSize() + 315, Color.GRAY.getRGB());

        fontRenderer.drawString(String.valueOf(playerSkills.getPoints()), (getX() - getWidthSize() + 97) - String.valueOf(playerSkills.getPoints()).length() * 2, getY() - 20, Color.DARK_GRAY.getRGB());
    }


    private void drawEntity(int posX, int posY, double scale, float mouseX, float mouseY, EntityLivingBase ent) {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) posX, (float) posY, 50.0F);
        GlStateManager.scale((float) (-scale), (float) scale, (float) scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float) Math.atan((mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        ent.renderYawOffset = (float) Math.atan((mouseX / 40.0F)) * 20.0F;
        ent.rotationYaw = (float) Math.atan((mouseX / 40.0F)) * 40.0F;
        ent.rotationPitch = -((float) Math.atan((mouseY / 40.0F))) * 20.0F;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    public void drawProgressBar(int exp, int expNeed) {
        int x = getX() - 180;
        int y = getY() - getHeightSize() + 108;
        int barSize = 124;
        int coloredCharacter = (int) (barSize * (100 * exp / expNeed) / 100F);

        mc.getTextureManager().bindTexture(PROGRESS_BAR);
        drawScaledCustomSizeModalRect(x, y, 0, 0, barSize, barSize, 163, 10, barSize, barSize);
        mc.getTextureManager().bindTexture(PROGRESS_BAR_FILLED);
        drawScaledCustomSizeModalRect(x + 1, y + 1, 0, 0, 30, 5, coloredCharacter, 8, 10, 10);
    }


}
