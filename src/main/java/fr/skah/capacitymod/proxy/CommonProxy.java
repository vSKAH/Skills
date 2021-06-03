package fr.skah.capacitymod.proxy;

/*
 *  * @Created on jeudi/mai/2021 - 16:56
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import fr.skah.capacitymod.CapacityMod;
import fr.skah.capacitymod.capability.DefaultPlayerSkills;
import fr.skah.capacitymod.capability.IPlayerSkills;
import fr.skah.capacitymod.capability.PlayerSkillsStorage;
import fr.skah.capacitymod.listeners.CapabilityListener;
import fr.skah.capacitymod.listeners.BlockListener;
import fr.skah.capacitymod.listeners.KillEntityListener;
import fr.skah.capacitymod.network.LevelUpPacket;
import fr.skah.capacitymod.network.RequestSynchronisePacket;
import fr.skah.capacitymod.network.RequestUpgrade;
import fr.skah.capacitymod.network.SynchroniseSkillsPacket;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {

    public void registerPackets() {
        CapacityMod.NETWORK_WRAPPER.registerMessage(RequestSynchronisePacket.ServerHandler.class, RequestSynchronisePacket.class, 0, Side.SERVER);
        CapacityMod.NETWORK_WRAPPER.registerMessage(SynchroniseSkillsPacket.ClientHandler.class, SynchroniseSkillsPacket.class, 1, Side.CLIENT);
        CapacityMod.NETWORK_WRAPPER.registerMessage(LevelUpPacket.ClientHandler.class, LevelUpPacket.class, 2, Side.CLIENT);
        CapacityMod.NETWORK_WRAPPER.registerMessage(RequestUpgrade.ServerHandler.class, RequestUpgrade.class, 3, Side.SERVER);
    }

    public void registerCapabilites() {
        CapabilityManager.INSTANCE.register(IPlayerSkills.class, new PlayerSkillsStorage(), DefaultPlayerSkills::new);
    }

    public void registerListeners() {
        MinecraftForge.EVENT_BUS.register(new CapabilityListener());
        MinecraftForge.EVENT_BUS.register(new KillEntityListener());
        MinecraftForge.EVENT_BUS.register(new BlockListener());
    }

    public void clientSide(Side side) {

    }

}
