package org.yuno.koldunswand.WandTasks.Spells;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.yuno.koldunswand.KoldunsWand;

public class Acsio {

    public void acsioAction(Player p) {
        int acsioBlockLimit = KoldunsWand.getInstance().getWandConfig().getMobSpellsBlockLimit();

        if (p.rayTraceEntities(acsioBlockLimit) != null) {

            Entity entity = p.rayTraceEntities(acsioBlockLimit).getHitEntity();

            p.getWorld().playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 1, 1);

            Location entityLoc = entity.getLocation();
            Location playerLoc = p.getLocation();

            double x = playerLoc.getX() - entityLoc.getX();
            double y = playerLoc.getY() - entityLoc.getY();
            double z = playerLoc.getZ() - entityLoc.getZ();

            double distance = Math.sqrt(x * x + y * y + z * z);

            double xVelocity = x / distance * 2;
            double yVelocity = y / distance * 2;
            double zVelocity = z / distance * 2;

            entity.setVelocity(new Vector(xVelocity, yVelocity, zVelocity));

            final Location particleLocation = p.getLocation().add(0, 1, 0);

            for (double waypoint = 1; waypoint < acsioBlockLimit; waypoint += 0.3) {
                final Vector vector = particleLocation.getDirection().multiply(waypoint);
                particleLocation.add(vector);

                if (particleLocation.getBlock().getType() != Material.AIR) {
                    break;
                }

                particleLocation.getWorld().spawnParticle(Particle.DUST, particleLocation, 3, 0.1, 0.1, 0.1, 0, new Particle.DustOptions(Color.BLUE, 0.7F));
            }
        } else {
            p.sendActionBar(Component.text("На месте палочки ")
                    .append(Component.text("нет существ!").color(NamedTextColor.BLUE)));
            p.playSound(p, Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
        }
    }
}
