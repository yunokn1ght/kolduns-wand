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

public class Bombard {

    public static void bombardAction(Player p) {
        int bombardBlockLimit = KoldunsWand.getInstance().getWandConfig().getBombardBlockLimit();

        if(p.rayTraceBlocks(bombardBlockLimit) != null
                && p.rayTraceBlocks(bombardBlockLimit).getHitBlock() != null) {
            Location bombardLocation = p.rayTraceBlocks(bombardBlockLimit).getHitBlock().getLocation();

            p.getWorld().createExplosion(bombardLocation, 5, false);

            final Location location = p.getLocation().add(0, 0.5, 0);

            for (double waypoint = 1; waypoint < bombardBlockLimit; waypoint += 0.3) {
                final Vector vector = location.getDirection().multiply(waypoint);
                location.add(vector);

                if (location.getBlock().getType() != Material.AIR) {
                    break;
                }

                location.getWorld().spawnParticle(Particle.FLAME, location, 3, 0.1, 0.1, 0.1, 0);
            }
        } else {
            p.sendActionBar(Component.text("Палочка ")
                    .append(Component.text("не достает").color(NamedTextColor.RED))
                    .append(Component.text(" до места!")));
            p.playSound(p, Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
        }
    }
}
