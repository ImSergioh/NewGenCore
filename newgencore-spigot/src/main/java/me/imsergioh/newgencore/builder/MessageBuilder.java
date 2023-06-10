package me.imsergioh.newgencore.builder;

import me.imsergioh.newgencore.util.ChatUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.awt.*;

public class MessageBuilder {

    public TextComponent textComponent;

    public MessageBuilder(String message) {
        textComponent = new TextComponent(ChatUtil.color(message));
    }

    public MessageBuilder bold(boolean active) {
        textComponent.setBold(active);
        return this;
    }

    public MessageBuilder underline(boolean active) {
        textComponent.setUnderlined(active);
        return this;
    }

    public MessageBuilder atClick(ClickEvent event) {
        textComponent.setClickEvent(event);
        return this;
    }

    public MessageBuilder atHover(HoverEvent event) {
        textComponent.setHoverEvent(event);
        return this;
    }

    public MessageBuilder color(String hexColor) {
        if (!hexColor.startsWith("#")) {
            hexColor = "#" + hexColor;
        }

        Color color = Color.decode(hexColor);
        textComponent.setColor(ChatColor.of(color));
        return this;
    }

    public TextComponent build() {
        return textComponent;
    }
}
