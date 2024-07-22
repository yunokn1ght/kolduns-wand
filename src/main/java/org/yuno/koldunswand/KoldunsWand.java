package org.yuno.koldunswand;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.yuno.koldunswand.WandTasks.ChangingSpell;
import org.yuno.koldunswand.WandTasks.Spells.Lumus;
import org.yuno.koldunswand.WandTasks.WandAbility;
import pl.mikigal.config.ConfigAPI;
import pl.mikigal.config.style.CommentStyle;
import pl.mikigal.config.style.NameStyle;

public final class KoldunsWand extends JavaPlugin {

    @Getter private static KoldunsWand instance;
    @Getter private WandConfig wandConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        wandConfig = ConfigAPI.init(
                WandConfig.class,
                NameStyle.UNDERSCORE,
                CommentStyle.INLINE,
                false,
                this
        );

        new WandItem();
        WandItem.wandItemStack();

        getCommand("wand").setExecutor(new WandCommand());
        getCommand("wand").setTabCompleter(new WandCommand());
        Bukkit.getPluginManager().registerEvents(new ChangingSpell(), this);
        Bukkit.getPluginManager().registerEvents(new WandAbility(), this);
        getLogger().info("KoldunsWand включен, idk что еще писать");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
