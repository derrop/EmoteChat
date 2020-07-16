package com.github.derrop.labymod.addons.emotechat.asm.packet;

import com.google.common.base.Preconditions;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C01PacketChatMessage;

import java.lang.reflect.Field;

public class PacketHandler {

    private static Field FIELD;

    static {
        try {
            FIELD = C01PacketChatMessage.class.getDeclaredField("message");
            FIELD.setAccessible(true);
        } catch (NoSuchFieldException exception) {
            try {
                FIELD = C01PacketChatMessage.class.getDeclaredField("a");
                FIELD.setAccessible(true);
            } catch (NoSuchFieldException exception1) {
                try {
                    FIELD = C01PacketChatMessage.class.getDeclaredField("field_149440_a");
                    FIELD.setAccessible(true);
                } catch (NoSuchFieldException exception2) {
                    throw new Error(exception2);
                }
            }
        }
    }

    private static ChatModifier chatModifier;

    public static void setChatModifier(ChatModifier chatModifier) {
        PacketHandler.chatModifier = chatModifier;
    }

    public static void handlePacket(Packet<?> packet) {
        if (chatModifier == null || !(packet instanceof C01PacketChatMessage)) {
            return;
        }

        C01PacketChatMessage message = (C01PacketChatMessage) packet;
        if (!chatModifier.shouldReplace(message.getMessage())) {
            return;
        }

        String result = chatModifier.replaceMessage(message.getMessage());
        Preconditions.checkNotNull(result);

        try {
            FIELD.set(message, result);
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

}