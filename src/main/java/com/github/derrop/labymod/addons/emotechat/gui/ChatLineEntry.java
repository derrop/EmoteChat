package com.github.derrop.labymod.addons.emotechat.gui;

import com.github.derrop.labymod.addons.emotechat.Constants;
import com.github.derrop.labymod.addons.emotechat.bttv.BTTVEmote;
import net.minecraft.client.gui.FontRenderer;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ChatLineEntry {

    private static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)§[0-9A-FK-OR]");

    private static final char COLOR_CHAR = 167;

    private final boolean emote;

    private final String content;

    private final String rawContent;

    private String colors;

    public ChatLineEntry(boolean emote, String content, String rawContent, String colors) {
        this.emote = emote;
        this.content = content;
        this.rawContent = rawContent;
        this.colors = colors;
    }

    public BTTVEmote getAsEmote() {
        return BTTVEmote.getByGlobalIdentifier(this.content);
    }

    public boolean isEmote() {
        return this.emote;
    }

    public String getContent() {
        return this.content;
    }

    public String getColors() {
        return this.colors;
    }

    // TODO: spaces are not displayed with fat (§l) strings
    public static Collection<ChatLineEntry> parseEntries(String line) {
        StringBuilder currentLine = new StringBuilder();

        return Arrays.stream(line.split(" ")).map(word -> {
            String strippedWord = STRIP_COLOR_PATTERN.matcher(word).replaceAll("");
            boolean emote = strippedWord.length() > 2
                    && strippedWord.charAt(0) == Constants.EMOTE_WRAPPER && strippedWord.charAt(strippedWord.length() - 1) == Constants.EMOTE_WRAPPER;

            String colors = FontRenderer.getFormatFromString(currentLine.toString());
            currentLine.append(word);

            return new ChatLineEntry(emote, (emote ? strippedWord.substring(1, strippedWord.length() - 1) : word), word, colors);
        }).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "ChatLineEntry{" +
                "emote=" + emote +
                ", content='" + content + '\'' +
                '}';
    }
}
