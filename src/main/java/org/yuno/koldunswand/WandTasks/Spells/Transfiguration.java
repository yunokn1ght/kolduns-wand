package org.yuno.koldunswand.WandTasks.Spells;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.yuno.koldunswand.KoldunsWand;

import java.util.Random;

public class Transfiguration {

    public void transfigurationAction(Player p) {
        int transfigurationBlockLimit = KoldunsWand.getInstance().getWandConfig().getMobSpellsBlockLimit();

        if (p.rayTraceEntities(transfigurationBlockLimit) != null) {

            Entity entity = p.rayTraceEntities(transfigurationBlockLimit).getHitEntity();

            p.getWorld().playSound(p.getLocation(), Sound.BLOCK_WET_SPONGE_DRIES, 1, 1);

            Location entityLoc = entity.getLocation();

            entity.remove();
            entity = entityLoc.getWorld().spawnEntity(entityLoc, EntityType.values()[new Random().nextInt(EntityType.values().length) - 1]);
            entity.getWorld().spawnParticle(Particle.TRIAL_OMEN, entity.getLocation(), 15, 0, 0.5, 0, 0.03);

            final Location particleLocation = p.getLocation().add(0, 1, 0);

            for (double waypoint = 1; waypoint < transfigurationBlockLimit; waypoint += 0.3) {
                final Vector vector = particleLocation.getDirection().multiply(waypoint);
                particleLocation.add(vector);

                if (particleLocation.getBlock().getType() != Material.AIR) {
                    break;
                }

                particleLocation.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, particleLocation, 3, 0.1, 0.1, 0.1, 0);
            }
        } else {
            p.sendActionBar(Component.text("На месте палочки ")
                    .append(Component.text("нет существ!").color(NamedTextColor.GOLD)));
            p.playSound(p, Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
        }
    }
}
