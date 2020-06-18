package com.github.derrop.labymod.addons.emotechat.gui.chatrender;

import com.github.derrop.labymod.addons.emotechat.EmoteChatAddon;
import net.labymod.ingamechat.IngameChatManager;
import net.labymod.ingamechat.renderer.types.ChatRendererMain;

public class EmoteChatRendererMain extends ChatRendererMain {

    private final EmoteChatRenderer renderer;

    public EmoteChatRendererMain(IngameChatManager manager, EmoteChatAddon addon) {
        super(manager);
        this.renderer = new EmoteChatRenderer(this, manager, addon);
    }

    @Override
    public void renderChat(int updateCounter) {
        this.renderer.renderChat(updateCounter);
    }

}
