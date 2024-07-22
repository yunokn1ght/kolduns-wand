package org.yuno.koldunswand.WandTasks.Spells;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.yuno.koldunswand.KoldunsWand;

public class AvadaKedavra {

    public void avadaKedavraAction(Player p) {
        int avBlockLimit = KoldunsWand.getInstance().getWandConfig().getMobSpellsBlockLimit();

        if (p.rayTraceEntities(avBlockLimit) != null) {

            Entity entity = p.rayTraceEntities(avBlockLimit).getHitEntity();

            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WARDEN_DEATH, 1, 1);

            Location entityLoc = entity.getLocation();
            Location playerLoc = p.getLocation();

            entity.remove();
            entity.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, entityLoc, 30, 0.5, 0.5, 0.5, 0.03);

            for (double waypoint = 1; waypoint < avBlockLimit; waypoint += 0.1) {
                final Vector vector = playerLoc.getDirection().multiply(waypoint);
                playerLoc.add(vector);

                if (playerLoc.getBlock().getType() != Material.AIR) {
                    break;
                }

                playerLoc.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, playerLoc, 5, 0.1, 0.1, 0.1, 0);
            }
        } else {
            p.sendActionBar(Component.text("На месте палочки ")
                    .append(Component.text("нет существ!").color(NamedTextColor.DARK_RED)));
            p.playSound(p, Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
        }
    }
}
