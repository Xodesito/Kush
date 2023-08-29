package com.kuma.kush.api.menu.button;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@Getter
@Setter
public class MenuButton {

    private final ItemStack itemStack;
    private Consumer<Player> whenClicked;

    public MenuButton(ItemStack itemStack) {
        this.itemStack = itemStack;
    }


}
