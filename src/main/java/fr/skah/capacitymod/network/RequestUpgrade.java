package fr.skah.capacitymod.network;

/*
 *  * @Created on samedi/mai/2021 - 21:01
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import fr.skah.capacitymod.capability.IPlayerSkills;
import fr.skah.capacitymod.capability.PlayerSkillsStorage;
import fr.skah.capacitymod.utils.Skills;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class RequestUpgrade implements IMessage {

    private String skillsType;

    public RequestUpgrade() {
    }

    public RequestUpgrade(String skillsType) {
        this.skillsType = skillsType;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        skillsType = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, skillsType);
    }

    public static class ServerHandler implements IMessageHandler<RequestUpgrade, IMessage> {

        @Override
        public IMessage onMessage(RequestUpgrade message, MessageContext ctx) {
            EntityPlayerMP entityPlayer = ctx.getServerHandler().player;
            Skills skill = Skills.fromName(message.skillsType);
            IPlayerSkills playerSkills = entityPlayer.getCapability(PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY, null);
            int pointNeeded = 0;

            switch (skill) {
                case VITALITY:
                case SAGE:
                    pointNeeded = 3;
                    break;
                case AIR:
                case INTELLIGENCE:
                case STRENGHT:
                case LUCK:
                    pointNeeded = 1;
                    break;
            }

            if (playerSkills.getPoints() >= pointNeeded) {
                playerSkills.setPoints(playerSkills.getPoints() - 1);
                if (skill == Skills.VITALITY) {
                    IAttributeInstance instance = entityPlayer.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
                    instance.applyModifier(new AttributeModifier(UUID.randomUUID(), "Health Modifier", 0.1F, 2).setSaved(true));
                    playerSkills.setVitalityLevel(playerSkills.getVitalityLevel() + 1);
                    playerSkills.setPoints(playerSkills.getPoints() -2);
                    return null;
                }

                if (skill == Skills.SAGE) {
                    playerSkills.setSageLevel(playerSkills.getSageLevel() + 1);
                    return null;
                }

                if (skill == Skills.STRENGHT) {
                    IAttributeInstance instance = entityPlayer.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
                    instance.applyModifier(new AttributeModifier(UUID.randomUUID(), "Streght Modifier", 0.2F, 2).setSaved(true));
                    playerSkills.setStrengthLevel(playerSkills.getStrengthLevel() + 1);
                    playerSkills.setPoints(playerSkills.getPoints() - 2);
                    return null;
                }

                if (skill == Skills.INTELLIGENCE) {
                    IAttributeInstance instance = entityPlayer.getEntityAttribute(SharedMonsterAttributes.ARMOR);
                    instance.applyModifier(new AttributeModifier(UUID.randomUUID(), "Resistance Modifier", 0.1F, 2).setSaved(true));
                    playerSkills.setIntelligenceLevel(playerSkills.getIntelligenceLevel() + 1);
                    return null;
                }

                if (skill == Skills.AIR) {
                    IAttributeInstance instance = entityPlayer.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
                    instance.applyModifier(new AttributeModifier(UUID.randomUUID(), "Speed Modifier", 0.2F, 2).setSaved(true));
                    playerSkills.setAirLevel(playerSkills.getAirLevel() + 1);
                    return null;
                }

                if (skill == Skills.LUCK) playerSkills.setLuckLevel(playerSkills.getLuckLevel() + 1);
            }

            return null;
        }
    }
}
