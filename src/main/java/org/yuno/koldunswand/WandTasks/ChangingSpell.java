package org.yuno.koldunswand.WandTasks;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.yuno.koldunswand.KoldunsWand;
import org.yuno.koldunswand.WandItem;

import java.util.List;


@Getter
public class ChangingSpell implements Listener {

    Inventory spellGUI = Bukkit.createInventory(null, 9, Component.text(KoldunsWand.getInstance().getWandConfig().getSpellMenuName()));


    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItem();

        if(item != null && e.getAction().isRightClick() && item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(KoldunsWand.getInstance(), "spell"))) {
            // ну да я взял предметы как у корперки и что вы мне сделаете :AJsunglasses:

            ItemStack bombard = ItemStack.of(Material.TNT);
            bombard.editMeta(meta -> {
                meta.displayName(Component.text("Бомбарда").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));

                meta.lore(List.of(Component.text("Призывает ").color(NamedTextColor.WHITE)
                        .append(Component.text("динамит").color(NamedTextColor.RED))
                        .append(Component.text(" на направление палочки").color(NamedTextColor.WHITE))
                        .decoration(TextDecoration.ITALIC, false)));
            });

            ItemStack spikus = ItemStack.of(Material.ICE);
            spikus.editMeta(meta -> {
                meta.displayName(Component.text("Спайкус").color(NamedTextColor.AQUA).decoration(TextDecoration.ITALIC, false));

                meta.lore(List.of(Component.text("Призывает ").color(NamedTextColor.WHITE)
                        .append(Component.text("ледяной столб").color(NamedTextColor.AQUA))
                        .append(Component.text(" под ногами волшебника").color(NamedTextColor.WHITE))
                        .decoration(TextDecoration.ITALIC, false)));
            });

            ItemStack baubillius = ItemStack.of(Material.LIGHTNING_ROD);
            baubillius.editMeta(meta -> {
                meta.displayName(Component.text("Баубиллиус").color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false));

                meta.lore(List.of(Component.text("Призывает ").color(NamedTextColor.WHITE)
                        .append(Component.text("молнию").color(NamedTextColor.GOLD))
                        .decoration(TextDecoration.ITALIC, false)));
            });

            ItemStack acsio = ItemStack.of(Material.WATER_BUCKET);
            acsio.editMeta(meta -> {
                meta.displayName(Component.text("Акцио").color(NamedTextColor.BLUE).decoration(TextDecoration.ITALIC, false));

                meta.lore(List.of(Component.text("Притягивает ").color(NamedTextColor.WHITE)
                        .append(Component.text("сущность или игрока").color(NamedTextColor.BLUE))
                        .decoration(TextDecoration.ITALIC, false)));
            });

            ItemStack lumus = ItemStack.of(Material.LIGHT);
            lumus.editMeta(meta -> {
                meta.displayName(Component.text("Люмус").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));

                meta.lore(List.of(Component.text("Волшебная палочка ").color(NamedTextColor.WHITE)
                        .append(Component.text("светится!").color(NamedTextColor.GOLD))
                        .decoration(TextDecoration.ITALIC, false)));
            });

            ItemStack transgression = ItemStack.of(Material.ENDER_PEARL);
            transgression.editMeta(meta -> {
                meta.displayName(Component.text("Трансгрессия").color(NamedTextColor.BLUE).decoration(TextDecoration.ITALIC, false));

                meta.lore(List.of(Component.text("Наводите палочку - ").color(NamedTextColor.WHITE)
                        .append(Component.text("телепортируетесь.").color(NamedTextColor.BLUE)
                        .append(Component.text(" Все просто.").color(NamedTextColor.WHITE)))
                        .decoration(TextDecoration.ITALIC, false)));
            });

            ItemStack transfiguration = ItemStack.of(Material.GOLD_INGOT);
            transfiguration.editMeta(meta -> {
                meta.displayName(Component.text("Трансфигурация").color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false));

                meta.lore(List.of(Component.text("Наведите палочку на сущность, которую желаете ").color(NamedTextColor.WHITE)
                        .append(Component.text("превратить в другую.").color(NamedTextColor.GOLD))
                        .decoration(TextDecoration.ITALIC, false)));
            });

            ItemStack avadaKedavra = ItemStack.of(Material.SKELETON_SKULL);
            avadaKedavra.editMeta(meta -> {
                meta.displayName(Component.text("Авада Кедавра").color(NamedTextColor.DARK_RED).decoration(TextDecoration.ITALIC, false));

                meta.lore(List.of(Component.text("Наведитесь на сущность, нажмите ПКМ ").color(NamedTextColor.WHITE)
                        .append(Component.text("и убейте ее.").color(NamedTextColor.DARK_RED))
                        .decoration(TextDecoration.ITALIC, false)));
            });


            spellGUI.setItem(0, bombard);
            spellGUI.setItem(1, spikus);
            spellGUI.setItem(2, baubillius);
            spellGUI.setItem(3, acsio);
            spellGUI.setItem(4, lumus);
            spellGUI.setItem(5, transgression);
            spellGUI.setItem(6, transfiguration);
            spellGUI.setItem(7, avadaKedavra);

            p.openInventory(spellGUI);
        }
    }

    @EventHandler
    public void onSpellClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null && e.getClickedInventory().equals(spellGUI)) {
            e.setCancelled(true);

            Player p = (Player) e.getWhoClicked();
            ItemStack item = e.getCurrentItem();
            assert item != null;
            final String spellName = PlainTextComponentSerializer.plainText().serialize(item.getItemMeta().displayName());
            KoldunsWand.getInstance().getLogger().info(spellName);

            ItemStack itemInMainHand = p.getInventory().getItemInMainHand();
            p.closeInventory();

            if(!item.getType().equals(Material.AIR)) {

                itemInMainHand.editMeta(meta -> meta.getPersistentDataContainer().set(WandItem.getSpellKey(), PersistentDataType.STRING, spellName));
                ItemStack newItem = WandItem.updateSpell(itemInMainHand);
                p.getInventory().setItemInMainHand(newItem);

                p.sendActionBar(Component.text("Вы выбрали заклинание ").color(NamedTextColor.WHITE)
                        .append(Component.text(spellName).color(item.getItemMeta().displayName().color()))
                        .append(Component.text(" для вашей палочки.").color(NamedTextColor.WHITE)));

                p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
            }
        }
    }
}
