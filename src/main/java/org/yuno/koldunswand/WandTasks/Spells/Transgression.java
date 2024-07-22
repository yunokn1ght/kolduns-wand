package org.yuno.koldunswand.WandTasks.Spells;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.yuno.koldunswand.KoldunsWand;

public class Transgression {

    public void transgressionAction(Player p) {
        int transgressionBlockLimit = KoldunsWand.getInstance().getWandConfig().getTransgressionBlockLimit();

        if(p.rayTraceBlocks(transgressionBlockLimit) != null
                && p.rayTraceBlocks(transgressionBlockLimit).getHitBlock() != null) {
            Location playerOldLocation = p.getLocation();

            for (double waypoint = 1; waypoint < transgressionBlockLimit; waypoint += 0.1) {
                final Vector vector = playerOldLocation.getDirection().multiply(waypoint);
                playerOldLocation.add(vector);

                if (playerOldLocation.getBlock().getType() != Material.AIR) {
                    final Vector vector2 = playerOldLocation.getDirection().multiply(-waypoint);
                    playerOldLocation.add(vector2);

                    p.teleport(playerOldLocation);
                    p.playSound(p, Sound.ENTITY_ZOMBIE_VILLAGER_CONVERTED, 1.0f, 1.0f);
                    break;
                }

                playerOldLocation.getWorld().spawnParticle(Particle.END_ROD, playerOldLocation, 10, 0.5, 0.5, 0.5, 0);
            }

        } else {
            p.sendActionBar(Component.text("Палочка ")
                    .append(Component.text("не достает").color(NamedTextColor.BLUE))
                    .append(Component.text(" до места!")));
            p.playSound(p, Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
        }
    }
}
