package fr.skah.capacitymod.network;

/*
 *  * @Created on jeudi/mai/2021 - 21:38
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import fr.skah.capacitymod.CapacityMod;
import fr.skah.capacitymod.capability.IPlayerSkills;
import fr.skah.capacitymod.capability.PlayerSkillsStorage;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class RequestSynchronisePacket implements IMessage {

    public RequestSynchronisePacket() {
    }


    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class ServerHandler implements IMessageHandler<RequestSynchronisePacket, IMessage> {

        @Override
        public IMessage onMessage(RequestSynchronisePacket message, MessageContext ctx) {
            EntityPlayerMP entityPlayer = ctx.getServerHandler().player;
            IPlayerSkills playerSkills = entityPlayer.getCapability(PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY, null);
            SynchroniseSkillsPacket synchroniseSkillsPacket = new SynchroniseSkillsPacket(playerSkills.getExperience(), playerSkills.getGlobalLevel(), playerSkills.getPoints(), playerSkills.getVitalityLevel(), playerSkills.getSageLevel(), playerSkills.getStrengthLevel(), playerSkills.getIntelligenceLevel(), playerSkills.getLuckLevel(), playerSkills.getAirLevel());
            CapacityMod.NETWORK_WRAPPER.sendTo(synchroniseSkillsPacket, entityPlayer);
            return null;
        }
    }
}
