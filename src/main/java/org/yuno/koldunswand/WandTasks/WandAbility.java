package org.yuno.koldunswand.WandTasks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.yuno.koldunswand.KoldunsWand;
import org.yuno.koldunswand.WandItem;
import org.yuno.koldunswand.WandTasks.Spells.*;

public class WandAbility implements Listener {

    @EventHandler
    public static void onWandAbility(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack itemInMainHand = e.getItem();

        if(e.getAction().isLeftClick() && itemInMainHand != null && itemInMainHand.hasItemMeta()
                && itemInMainHand.getItemMeta().getPersistentDataContainer().has(WandItem.getSpellKey())) {
            e.setCancelled(true);

            String spellName = itemInMainHand.getItemMeta().getPersistentDataContainer().get(WandItem.getSpellKey(), PersistentDataType.STRING);
            assert spellName != null;
            KoldunsWand.getInstance().getLogger().info(spellName);

            switch(spellName) {
                case ("Бомбарда"): Bombard.bombardAction(p); break;
                case ("Спайкус"): new Spikus().spikusAction(p); break;
                case ("Баубиллиус"): new Baubillius().baubilliusAction(p); break;
                case ("Акцио"): new Acsio().acsioAction(p); break;
                case ("Люмус"): Lumus.LumusAction(p); break;
                case ("Трансгрессия"): new Transgression().transgressionAction(p); break;
                case ("Трансфигурация"): new Transfiguration().transfigurationAction(p); break;
                case ("Авада Кедавра"): new AvadaKedavra().avadaKedavraAction(p); break;
            }

        }
    }
}
