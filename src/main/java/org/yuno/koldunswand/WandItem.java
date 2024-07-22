package org.yuno.koldunswand;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;


public class WandItem {

    @Getter private static final ItemStack wand = new ItemStack(Material.valueOf(KoldunsWand.getInstance().getWandConfig().getMaterial()));
    private static final ArrayList<Component> lore = new ArrayList<>();
    @Getter private static NamespacedKey spellKey = new NamespacedKey(KoldunsWand.getInstance(), "spell");

    public static void wandItemStack() {

        Component displayName = Component.text(KoldunsWand.getInstance().getWandConfig().getName())
                .color(TextColor.fromHexString(KoldunsWand.getInstance().getWandConfig().getColor()))
                .decoration(TextDecoration.ITALIC, false);

        if(!(KoldunsWand.getInstance().getWandConfig().getTextDecoration().equalsIgnoreCase("null"))) {
            displayName.decoration(TextDecoration.valueOf(KoldunsWand.getInstance().getWandConfig().getTextDecoration()));
        }

        ItemMeta meta = wand.getItemMeta();
        meta.displayName(displayName);
        meta.getPersistentDataContainer().set(spellKey, PersistentDataType.STRING, "Бомбарда");

        lore.add(Component.text(" ").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("Волшебная палочка!").color(TextColor.fromHexString(KoldunsWand.getInstance().getWandConfig().getColor())).decoration(TextDecoration.ITALIC, false));

        lore.add(
                Component.text("[ПКМ]").color(TextColor.fromHexString(KoldunsWand.getInstance().getWandConfig().getColor()))
                .append(Component.text(" - Выбрать заклинание").color(NamedTextColor.WHITE))
                .decoration(TextDecoration.ITALIC, false)
        );

        lore.add(
                Component.text("[ЛКМ]").color(TextColor.fromHexString(KoldunsWand.getInstance().getWandConfig().getColor()))
                        .append(Component.text(" - Использовать заклинание").color(NamedTextColor.WHITE))
                        .decoration(TextDecoration.ITALIC, false)
        );

        lore.add(Component.text(" ").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore.add(
                Component.text("Текущее заклинание").color(TextColor.fromHexString(KoldunsWand.getInstance().getWandConfig().getColor()))
                        .append(Component.text(" - " + meta.getPersistentDataContainer().get(spellKey, PersistentDataType.STRING)).color(NamedTextColor.WHITE))
                        .decoration(TextDecoration.ITALIC, false)
        );

        meta.lore(lore);
        meta.addEnchant(Enchantment.FLAME, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        wand.setItemMeta(meta);
    }

    public static ItemStack updateSpell(ItemStack item) {

        ItemMeta meta = item.getItemMeta();

        String spell = meta.getPersistentDataContainer().get(
                new NamespacedKey(KoldunsWand.getInstance(), "spell"),
                PersistentDataType.STRING);
        ArrayList<Component> lore = (ArrayList<Component>) meta.lore();

        lore.set(lore.size() - 1, Component.text("Текущее заклинание")
                .color(TextColor.fromHexString(KoldunsWand.getInstance().getWandConfig().getColor()))
                .append(Component.text(" - " + spell).color(NamedTextColor.WHITE))
                .decoration(TextDecoration.ITALIC, false));

        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
