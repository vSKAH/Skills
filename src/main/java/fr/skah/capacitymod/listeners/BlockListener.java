package fr.skah.capacitymod.listeners;

/*
 *  * @Created on dimanche/mai/2021 - 01:44
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import fr.skah.capacitymod.capability.IPlayerSkills;
import fr.skah.capacitymod.capability.PlayerSkillsStorage;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockListener {


    @SubscribeEvent
    public void onBreakBlock(BlockEvent.BreakEvent event) {

        EntityPlayer player = event.getPlayer();
        IPlayerSkills playerSkills = player.getCapability(PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY, null);

        if (playerSkills.getLuckLevel() == 0 || player.isCreative()) return;

        if (EnchantmentHelper.getEnchantments(player.getHeldItem(EnumHand.MAIN_HAND)).containsKey(Enchantment.getEnchantmentByID(35)))
            return;


        BlockPos pos = event.getPos();
        IBlockState blockState = event.getWorld().getBlockState(pos);
        Block block = blockState.getBlock();
        block.dropBlockAsItemWithChance(event.getWorld(), pos, blockState, 20, playerSkills.getLuckLevel());
    }
}
