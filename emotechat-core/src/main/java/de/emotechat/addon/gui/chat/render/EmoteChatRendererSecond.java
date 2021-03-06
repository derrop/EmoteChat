package de.emotechat.addon.gui.chat.render;

import de.emotechat.addon.EmoteChatAddon;
import net.labymod.ingamechat.IngameChatManager;
import net.labymod.ingamechat.renderer.ChatLine;
import net.labymod.ingamechat.renderer.types.ChatRendererSecond;
import net.minecraft.client.gui.GuiChat;

import java.util.List;

public class EmoteChatRendererSecond extends ChatRendererSecond implements EmoteChatRendererType {

    private final EmoteChatRenderer renderer;

    private GuiChat lastGuiChat;

    public EmoteChatRendererSecond(IngameChatManager manager, EmoteChatAddon addon, List<ChatLine> lines) {
        super(manager);
        super.getChatLines().addAll(lines);
        this.renderer = new EmoteChatRenderer(this, manager, addon);
    }

    @Override
    public void addChatLine(String message, boolean secondChat, String room, Object component, int updateCounter, int chatLineId, Integer highlightColor, boolean refresh) {
        if (this.renderer.addChatLine(message, secondChat, room, component, updateCounter, chatLineId, highlightColor, refresh)) {
            super.addChatLine(message, secondChat, room, component, updateCounter, chatLineId, highlightColor, refresh);
        }
    }

    @Override
    public void renderChat(int updateCounter) {
        this.renderer.renderChat(updateCounter);
    }

    @Override
    public void renderDefault(int updateCounter) {
        super.renderChat(updateCounter);
    }

    @Override
    public float getChatPositionY() {
        return this.renderer.getChatPositionY();
    }

    @Override
    public boolean renderHoveringResizeX(boolean forceRender) {
        return this.renderer.renderHoveringResizeX(forceRender);
    }

    @Override
    public boolean renderHoveringResizeY(boolean forceRender) {
        return this.renderer.renderHoveringResizeY(forceRender);
    }

    @Override
    public GuiChat getLastChatGui() {
        return this.lastGuiChat;
    }

    public void setLastGuiChat(GuiChat lastGuiChat) {
        this.lastGuiChat = lastGuiChat;
    }
}
