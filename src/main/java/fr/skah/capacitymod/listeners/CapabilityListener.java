package fr.skah.capacitymod.listeners;

/*
 *  * @Created on jeudi/mai/2021 - 18:17
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import fr.skah.capacitymod.CapacityMod;
import fr.skah.capacitymod.capability.IPlayerSkills;
import fr.skah.capacitymod.capability.PlayerSkillsProvider;
import fr.skah.capacitymod.capability.PlayerSkillsStorage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityListener {

    public static final ResourceLocation CAPABILITY_LOCATION = new ResourceLocation(CapacityMod.MODID, "playerskills");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof EntityPlayer && !event.getObject().hasCapability(PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY, null)) {
            event.addCapability(CAPABILITY_LOCATION, new PlayerSkillsProvider());
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        EntityPlayer player = event.getEntityPlayer();

        IPlayerSkills capabilityOld = event.getOriginal().getCapability(PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY, null);
        IPlayerSkills capabilityNew = player.getCapability(PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY, null);
        capabilityNew.set(capabilityOld.get());
    }
}

