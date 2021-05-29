package fr.skah.capacitymod.listeners;

/*
 *  * @Created on vendredi/mai/2021 - 01:40
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import fr.skah.capacitymod.CapacityMod;
import fr.skah.capacitymod.gui.CapacityGui;
import fr.skah.capacitymod.network.RequestSynchronisePacket;
import fr.skah.capacitymod.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyInputListeners {

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event) {
        if (ClientProxy.getLevelsKeybind().isPressed()) {
            CapacityMod.NETWORK_WRAPPER.sendToServer(new RequestSynchronisePacket());
            Minecraft.getMinecraft().displayGuiScreen(new CapacityGui());
        }
    }

}
