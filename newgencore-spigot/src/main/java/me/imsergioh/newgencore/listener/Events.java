package me.imsergioh.newgencore.listener;

import me.imsergioh.newgencore.builder.MessageBuilder;
import me.imsergioh.newgencore.util.ChatUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        ChatUtil.send(event.getPlayer(),
                new MessageBuilder
                        ("Smart&f&lMC")
                        .color("99FFFF")
                        .bold(true)
                        .atClick(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, "play.smartmc.us"))
                        .atHover(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Clic para copiar ip").create())),
                new MessageBuilder
                        (" Network")
                        .color("D0D0D0"));
    }
}
