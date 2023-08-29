package com.kuma.kush.api.menu;

import com.kuma.kush.api.menu.button.MenuButton;
import com.kuma.kush.api.menu.manager.MenuManager;
import com.kuma.kush.api.menu.pattern.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static com.kuma.kush.api.text.TextUtil.format;

@Getter
@Setter
public class Menu {

    private final JavaPlugin plugin;
    private final String title;
    private final int rows;
    private final Pattern pattern;
    private final Inventory inventory;

    private final Map<Integer, MenuButton> buttonMap;
    private Consumer<Player> inventoryClosed;
    private Consumer<Player> inventoryOpened;
    private BukkitRunnable menuUpdater;

    public Menu(JavaPlugin plugin, String title, int rows, Pattern pattern) {
        this.plugin = plugin;
        this.title = title;
        this.rows = rows;
        this.pattern = pattern;

        if (rows > 6 || rows < 1 || title.length() > 32) {
            throw new IllegalArgumentException("Invalid menu parameters! Rows must be between 1 and 6, and title must be less than 32 characters!");
        }

        this.inventory = Bukkit.createInventory(null, rows * 9, format(title));
        this.buttonMap = new HashMap<>();
        pattern.applyToMenu(this);
    }

    public void registerButton(MenuButton menuButton, int slot) {
        buttonMap.put(slot, menuButton);
    }

    public void createMenuUpdater() {
        menuUpdater = new BukkitRunnable() {
            @Override
            public void run() {
                onUpdate();
            }
        };
        menuUpdater.runTaskTimer(plugin, 0, 20);
    }

    public void onUpdate() {
    }

    public void handleClose(Player player) {
        if (inventoryClosed != null) {
            inventoryClosed.accept(player);
        }
    }

    public void handleOpen(Player player) {
        if (inventoryOpened != null) {
            inventoryOpened.accept(player);
        }
    }

    public void handleClick(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);
        ItemStack clickedItem = inventoryClickEvent.getCurrentItem();

        if (clickedItem == null) {
        }

        if (buttonMap.containsKey(inventoryClickEvent.getSlot())) {


            Consumer<Player> consumer = buttonMap.get(inventoryClickEvent.getRawSlot()).getWhenClicked();

            if (consumer != null) {
                consumer.accept((Player) inventoryClickEvent.getWhoClicked());
            }
        }
    }

    public void open(Player player) {
        MenuManager manager = MenuManager.getInstance();
        buttonMap.forEach((slot, button) -> inventory.setItem(slot, button.getItemStack()));
        player.openInventory(inventory);
        manager.registerMenu(player.getUniqueId(), this);
        handleOpen(player);
    }

}
