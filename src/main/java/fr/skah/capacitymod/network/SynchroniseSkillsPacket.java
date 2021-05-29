package fr.skah.capacitymod.network;

/*
 *  * @Created on jeudi/mai/2021 - 21:02
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import fr.skah.capacitymod.capability.IPlayerSkills;
import fr.skah.capacitymod.capability.PlayerSkillsStorage;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SynchroniseSkillsPacket implements IMessage {

    private int experience;
    private int globalLevel;
    private int points;

    private int vitalityLevel;
    private int sageLevel;
    private int strenghtLevel;
    private int intelligenceLevel;
    private int luckLevel;
    private int airLevel;

    public SynchroniseSkillsPacket() {
    }

    public SynchroniseSkillsPacket(int experience, int globalLevel, int points, int vitalityLevel, int sageLevel, int strenghtLevel, int intelligenceLevel, int luckLevel, int airLevel) {
        this.experience = experience;
        this.globalLevel = globalLevel;
        this.points = points;
        this.vitalityLevel = vitalityLevel;
        this.sageLevel = sageLevel;
        this.strenghtLevel = strenghtLevel;
        this.intelligenceLevel = intelligenceLevel;
        this.luckLevel = luckLevel;
        this.airLevel = airLevel;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        experience = buf.readInt();
        globalLevel = buf.readInt();
        points = buf.readInt();

        vitalityLevel = buf.readInt();
        sageLevel = buf.readInt();
        strenghtLevel = buf.readInt();
        intelligenceLevel = buf.readInt();
        luckLevel = buf.readInt();
        airLevel = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(experience);
        buf.writeInt(globalLevel);
        buf.writeInt(points);

        buf.writeInt(vitalityLevel);
        buf.writeInt(sageLevel);
        buf.writeInt(strenghtLevel);
        buf.writeInt(intelligenceLevel);
        buf.writeInt(luckLevel);
        buf.writeInt(airLevel);
    }

    public static class ClientHandler implements IMessageHandler<SynchroniseSkillsPacket, IMessage> {

        @Override
        public IMessage onMessage(SynchroniseSkillsPacket message, MessageContext ctx) {
            EntityPlayerSP clientPlayer = Minecraft.getMinecraft().player;
            IPlayerSkills playerData = clientPlayer.getCapability(PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY, null);

            playerData.setExperience(message.experience);
            playerData.setGlobalLevel(message.globalLevel);
            playerData.setPoints(message.points);

            playerData.setVitalityLevel(message.vitalityLevel);
            playerData.setSageLevel(message.sageLevel);
            playerData.setStrengthLevel(message.strenghtLevel);
            playerData.setIntelligenceLevel(message.intelligenceLevel);
            playerData.setLuckLevel(message.luckLevel);
            playerData.setAirLevel(message.airLevel);
            return null;
        }
    }
}
