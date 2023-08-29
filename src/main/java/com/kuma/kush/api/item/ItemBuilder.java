package com.kuma.kush.api.item;


import com.kuma.kush.api.item.enchantment.EnchantmentBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static com.kuma.kush.api.text.TextUtil.format;
import static com.kuma.kush.api.text.TextUtil.formatLore;

@SuppressWarnings("unused")
public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder displayName(String displayName) {
        itemMeta.setDisplayName(format(displayName));
        return this;
    }

    public ItemBuilder lore(String... lore) {
        itemMeta.setLore(formatLore(lore));
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        itemMeta.setLore(format(lore));
        return this;
    }

    public ItemBuilder amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder unbreakable(boolean unbreakable) {
        itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder customModelData(int customModelData) {
        itemMeta.setCustomModelData(customModelData);
        return this;
    }

    public ItemBuilder itemFlags(ItemFlag... itemFlags) {
        itemMeta.addItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder itemFlags(List<ItemFlag> itemFlags) {
        itemMeta.addItemFlags(itemFlags.toArray(new ItemFlag[0]));
        return this;
    }

    public ItemBuilder enchantment(EnchantmentBuilder enchantmentBuilder) {
        itemMeta.addEnchant(enchantmentBuilder.build().keySet().iterator().next(), enchantmentBuilder.build().values().iterator().next(), true);
        return this;
    }

    public ItemBuilder enchantment(EnchantmentBuilder... enchantmentBuilders) {
        for (EnchantmentBuilder enchantmentBuilder : enchantmentBuilders) {
            itemMeta.addEnchant(enchantmentBuilder.build().keySet().iterator().next(), enchantmentBuilder.build().values().iterator().next(), true);
        }
        return this;
    }

    public ItemBuilder enchantment(List<EnchantmentBuilder> enchantmentBuilders) {
        for (EnchantmentBuilder enchantmentBuilder : enchantmentBuilders) {
            itemMeta.addEnchant(enchantmentBuilder.build().keySet().iterator().next(), enchantmentBuilder.build().values().iterator().next(), true);
        }
        return this;
    }

    public ItemBuilder enchantment(EnchantmentBuilder enchantmentBuilder, boolean ignoreLevelRestriction) {
        itemStack.addUnsafeEnchantments(enchantmentBuilder.build());
        return this;
    }

    public ItemBuilder glow() {
        itemStack.addEnchantments(new EnchantmentBuilder().enchant(org.bukkit.enchantments.Enchantment.DURABILITY, 1).build());
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
