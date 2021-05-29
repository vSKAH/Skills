package fr.skah.capacitymod.listeners;

/*
 *  * @Created on jeudi/mai/2021 - 18:17
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import fr.skah.capacitymod.CapacityMod;
import fr.skah.capacitymod.capability.PlayerSkillsProvider;
import fr.skah.capacitymod.capability.PlayerSkillsStorage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AttachCapabilityListener {

    public static final ResourceLocation CAPABILITY_LOCATION = new ResourceLocation(CapacityMod.MODID, "playerskills");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof EntityPlayer && !event.getObject().hasCapability(PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY, null)) {
            event.addCapability(CAPABILITY_LOCATION, new PlayerSkillsProvider());
        }
    }

}
