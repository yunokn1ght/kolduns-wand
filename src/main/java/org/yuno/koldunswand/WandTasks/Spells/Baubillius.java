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

public class Baubillius {

    public void baubilliusAction(Player p) {
        int baubilliusBlockLimit = KoldunsWand.getInstance().getWandConfig().getBaubilliusBlockLimit();

        if (p.rayTraceBlocks(baubilliusBlockLimit) != null
                && p.rayTraceBlocks(baubilliusBlockLimit).getHitBlock() != null) {
            Location baubilliusLocation = p.rayTraceBlocks(baubilliusBlockLimit).getHitBlock().getLocation();

            p.getWorld().strikeLightning(baubilliusLocation);

            final Location location = p.getLocation().add(0, 1, 0);

            for (double waypoint = 1; waypoint < baubilliusBlockLimit; waypoint += 0.3) {
                final Vector vector = location.getDirection().multiply(waypoint);
                location.add(vector);

                if (location.getBlock().getType() != Material.AIR) {
                    break;
                }

                location.getWorld().spawnParticle(Particle.SNOWFLAKE, location, 3, 0.1, 0.1, 0.1, 0);
            }
        } else {
            p.sendActionBar(Component.text("Палочка ")
                    .append(Component.text("не достает").color(NamedTextColor.GOLD))
                    .append(Component.text(" до места!")));
            p.playSound(p, Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
        }
    }
}
