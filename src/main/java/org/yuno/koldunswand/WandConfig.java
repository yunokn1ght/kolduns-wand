package org.yuno.koldunswand;

import pl.mikigal.config.Config;
import pl.mikigal.config.annotation.Comment;
import pl.mikigal.config.annotation.ConfigName;

@ConfigName("config.yml")
public interface WandConfig extends Config {

    @Comment("Название палочки")
    default String getName() {
        return "Палочка-выручалочка";
    }

    @Comment("Если декорация не нужна, оставьте \"null\"")
    default String getTextDecoration() {
        return "null";
    }

    @Comment("Цвет в формате HEX")
    default String getColor() {
        return "#7f03fc";
    }

    @Comment("Материал палочки")
    default String getMaterial() {
        return "STICK";
    }

    @Comment("Название меню с выбором заклинания")
    default String getSpellMenuName() {
        return "Выбор заклинания";
    }

    @Comment("Лимит блоков для Бомбарды")
    default Integer getBombardBlockLimit() {
        return 10;
    }

    @Comment("Лимит блоков для Баубиллиуса")
    default Integer getBaubilliusBlockLimit() {
        return 15;
    }

    @Comment("Лимит блоков для заклинаний с мобами")
    default Integer getMobSpellsBlockLimit() {
        return 15;
    }

    @Comment("Лимит блоков для Трансгрессии")
    default Integer getTransgressionBlockLimit() {
        return 40;
    }
}
