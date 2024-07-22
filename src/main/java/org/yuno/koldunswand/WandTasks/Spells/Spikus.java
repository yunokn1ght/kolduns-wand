package org.yuno.koldunswand.WandTasks.Spells;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.yuno.koldunswand.KoldunsWand;

public class Spikus {

    @Getter int blocksCount = 0;

    public void spikusAction(Player p) {
        Bukkit.getScheduler().runTaskTimer(KoldunsWand.getInstance(), task -> {
            Location playerLocation = p.getLocation();
            p.teleport(p.getLocation().add(0, 1, 0));

            playerLocation.getBlock().setType(Material.BLUE_ICE);
            p.playSound(playerLocation, Sound.BLOCK_GLASS_BREAK, 1.0f, 1.0f);

            if(blocksCount >= 10) {
                task.cancel();
                blocksCount = 0;
            }

            blocksCount++;
        }, 0, 1);
    }
}
