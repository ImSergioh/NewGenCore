package me.imsergioh.newgencore.instance;

import com.google.common.collect.Lists;
import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import lombok.Getter;
import me.imsergioh.newgencore.IPluginScoreboard;
import me.imsergioh.newgencore.instance.config.PluginLocalConfig;
import me.imsergioh.newgencore.util.ChatUtil;
import me.imsergioh.newgencore.util.PluginUtil;
import org.bukkit.entity.Player;

import java.util.List;

public class PluginScoreboard implements IPluginScoreboard {

    @Getter
    private final String name;
    private final PluginLocalConfig config;

    public PluginScoreboard(String name, PluginLocalConfig config) {
        this.name = name;
        this.config = config;
    }

    @Override
    public void registerPlayer(Player player) {
        PluginUtil.sync(() -> {
            String title = getTitle();
            BPlayerBoard board = Netherboard.instance().getBoard(player);
            if (board == null) {
                Netherboard.instance().createBoard(player, title);
                board = Netherboard.instance().getBoard(player);
            } else {
                board.setName(title);
            }
            board.clear();
            setupLines(board);
        });
    }

    @Override
    public void unregisterPlayer(Player player) {
        BPlayerBoard board = Netherboard.instance().getBoard(player);
        if (board == null) return;
        board.delete();
    }

    public String getTitle() {
        return ChatUtil.color(config.getString("title"));
    }

    public void setupLines(BPlayerBoard board) {
        List<String> lines = Lists.reverse(getLines());
        int size = lines.size();
        for (int i = 0; i < size; i++) {
            String line = lines.get(i);
            board.set(ChatUtil.color(line), i);
        }
    }

    public List<String> getLines() {
        return config.getStringList("lines");
    }
}
