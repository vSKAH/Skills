package fr.skah.capacitymod;

import fr.skah.capacitymod.proxy.ClientProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = CapacityMod.MODID, name = CapacityMod.NAME, version = CapacityMod.VERSION)
public class CapacityMod {

    @SidedProxy(clientSide = "fr.skah.capacitymod.proxy.ClientProxy")
    private static ClientProxy proxy;

    public static final String MODID = "capacitymod";
    public static final String NAME = "Capacity Mod";
    public static final String VERSION = "1.0";
    public static final SimpleNetworkWrapper NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel("capacitymod");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        getProxy().registerPackets();
    }


    @EventHandler
    public void init(FMLInitializationEvent event) {
        getProxy().registerCapabilites();
        getProxy().registerListeners();
        getProxy().clientSide(event.getSide());
    }

    public static ClientProxy getProxy() {
        return proxy;
    }
}
