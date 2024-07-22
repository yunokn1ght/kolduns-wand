package org.yuno.koldunswand.WandTasks.Spells;


import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Lumus {

    @Getter private static Block block;

    public static void LumusAction(Player p) {
        if(block != null)
            block.setType(Material.AIR);

        block = p.getLocation().add(0, 1, 0).getBlock();
        block.setType(Material.LIGHT);
    }
}
