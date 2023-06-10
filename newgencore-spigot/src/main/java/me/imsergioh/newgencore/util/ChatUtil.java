package me.imsergioh.newgencore.util;

import me.imsergioh.newgencore.builder.MessageBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ChatUtil {

    // SEND METHOD WITH MESSAGE BUILDERS
    public static void send(Player player, MessageBuilder... builders) {
        TextComponent finalMessageComponent = new TextComponent("");
        for (MessageBuilder builder : builders) {
            finalMessageComponent.addExtra(builder.build());
        }
        player.spigot().sendMessage(finalMessageComponent);
    }

    public static String color(String message) {
        return message.replace('&', '§');
    }
}
