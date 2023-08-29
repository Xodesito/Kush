package com.kuma.kush.api.item.enchantment;

import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentBuilder {

    private final Map<Enchantment, Integer> enchantments = new HashMap<>();

    public EnchantmentBuilder enchant(Enchantment enchantment, int level) {
        enchantments.put(enchantment, level);
        return this;
    }

    public EnchantmentBuilder enchant(Enchantment enchantment) {
        enchantments.put(enchantment, 1);
        return this;
    }

    public Map<Enchantment, Integer> build() {
        return enchantments;
    }

}
