package fr.skah.capacitymod.network;

/*
 *  * @Created on samedi/mai/2021 - 02:39
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import fr.skah.capacitymod.listeners.OverlayListeners;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LevelUpPacket implements IMessage {

    private int globalLevel;

    public LevelUpPacket() {
    }

    public LevelUpPacket(int globalLevel) {
        this.globalLevel = globalLevel;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        globalLevel = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(globalLevel);
    }

    public static class ClientHandler implements IMessageHandler<LevelUpPacket, IMessage> {

        @Override
        public IMessage onMessage(LevelUpPacket message, MessageContext ctx) {
            OverlayListeners.LEVEL_UP = 750;
            OverlayListeners.PLAYER_LEVEL = message.globalLevel;
            return null;
        }
    }
}
