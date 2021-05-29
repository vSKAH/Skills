package fr.skah.capacitymod.listeners;

/*
 *  * @Created on jeudi/mai/2021 - 20:44
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import fr.skah.capacitymod.CapacityMod;
import fr.skah.capacitymod.capability.IPlayerSkills;
import fr.skah.capacitymod.capability.PlayerSkillsStorage;
import fr.skah.capacitymod.network.LevelUpPacket;
import fr.skah.capacitymod.utils.Level;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class KillEntityListener {


    @SubscribeEvent
    public void onPlayerKillMob(LivingDeathEvent event) {

        if (!(event.getSource().getTrueSource() instanceof EntityPlayer)) return;
        if (event.getEntity() instanceof EntityAnimal || event.getEntity() instanceof EntityMob) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            int addedExperience = 0;
            switch (event.getEntity().getName()) {
                case "Sheep":
                case "Pig":
                case "Chicken":
                    addedExperience = 15;
                    break;
                case "Zombie":
                case "Zombie Villager":
                case "Skeleton":
                case "Spider":
                case "Cave Spider":
                case "Enderman":
                    addedExperience = 20;
                    break;
                case "Witch":
                case "Slime":
                case "Silverfish":
                case "Zombie Pigman":
                case "Blaze":
                    addedExperience = 25;
                    break;
                case "Wither Skeleton":
                case "Guardian":
                    addedExperience = 30;
                    break;
                case "Elder Guardian":
                    addedExperience = 40;
                    break;
                case "Wither":
                    addedExperience = 100;
                    break;
                case "Ender Dragon":
                    addedExperience = 120;
                    break;
                default:
                    System.out.println("Le mob " + event.getEntity().getName() + " n'exite pas !");
                    break;
            }
            IPlayerSkills playerSkills = player.getCapability(PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY, null);
            int experienceGain = addedExperience + (playerSkills.getSageLevel() * 4) + (playerSkills.getGlobalLevel() * 2 - 2);
            updatePlayerExperience(player, playerSkills, playerSkills.getExperience() + experienceGain);
            if (!player.world.isRemote) {
                OverlayListeners.EXP_UP = 1200;
                OverlayListeners.EXP = experienceGain;
            }
        }
    }

    @SubscribeEvent
    public void onDrop(LivingDropsEvent event) {
        if (!(event.getSource().getTrueSource() instanceof EntityPlayer) || event.getEntity() instanceof EntityPlayer)
            return;

        EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
        if (EnchantmentHelper.getEnchantments(player.getHeldItem(EnumHand.MAIN_HAND)).containsKey(Enchantment.getEnchantmentByID(21)))
            return;

        IPlayerSkills playerSkills = player.getCapability(PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY, null);
        event.getDrops().forEach(item -> {
            ItemStack stack = item.getItem();
            stack.setCount(item.getItem().getCount() * playerSkills.getLuckLevel());
            item.setItem(stack);
        });
    }


    private void updatePlayerExperience(EntityPlayer player, IPlayerSkills playerSkills, int experience) {
        playerSkills.setExperience(experience);
        if (playerSkills.getExperience() >= Level.levelToExp(playerSkills.getGlobalLevel())) {
            playerSkills.setGlobalLevel(playerSkills.getGlobalLevel() + 1);
            playerSkills.setExperience(0);
            playerSkills.setPoints(playerSkills.getPoints());
            CapacityMod.NETWORK_WRAPPER.sendTo(new LevelUpPacket(playerSkills.getGlobalLevel()), (EntityPlayerMP) player);
        }
    }
}
