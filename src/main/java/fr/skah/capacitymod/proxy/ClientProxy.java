package fr.skah.capacitymod.proxy;

/*
 *  * @Created on jeudi/mai/2021 - 16:56
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import fr.skah.capacitymod.CapacityMod;
import fr.skah.capacitymod.listeners.KeyInputListeners;
import fr.skah.capacitymod.listeners.OverlayListeners;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {

    private static final KeyBinding LEVELS_KEYBIND = new KeyBinding("Ouvrir le menu des niveaux", Keyboard.KEY_P, "key.categories.gameplay");
    public static final ResourceLocation LEVEL_UP_OVERLAY = new ResourceLocation(CapacityMod.MODID, "textures/gui/level_up_banner.png");

    @Override
    public void clientSide(Side side) {
        if(side.isClient()) {
            ClientRegistry.registerKeyBinding(LEVELS_KEYBIND);
            MinecraftForge.EVENT_BUS.register(new KeyInputListeners());
            MinecraftForge.EVENT_BUS.register(new OverlayListeners());
        }
    }

    public static KeyBinding getLevelsKeybind() {
        return LEVELS_KEYBIND;
    }


}
