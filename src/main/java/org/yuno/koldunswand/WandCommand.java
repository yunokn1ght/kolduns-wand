package org.yuno.koldunswand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WandCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length < 2) {
            commandSender.sendMessage(strings.length == 0 ? "Выберите игрока!" : "Выберите игрока и число палочек!");
            return true;
        }

        Player p = Bukkit.getPlayer(strings[0]);

        if (p != null && p.isOnline()) {
            p.getInventory().addItem(WandItem.getWand());

        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length == 0) return new ArrayList<>(Bukkit.getOnlinePlayers().stream().map(Player::getName).toList());

        return null;
    }
}
