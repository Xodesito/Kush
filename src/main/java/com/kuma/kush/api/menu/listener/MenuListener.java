package com.kuma.kush.api.menu.listener;

import com.kuma.kush.api.menu.Menu;
import com.kuma.kush.api.menu.manager.MenuManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        Menu matchedMenu = MenuManager.getInstance().matchMenu(event.getWhoClicked().getUniqueId());

        if (matchedMenu != null) {
            matchedMenu.handleClick(event);
        }
    }

    @EventHandler
    public void InventoryClose(InventoryCloseEvent event) {
        Menu matchedMenu = MenuManager.getInstance().matchMenu(event.getPlayer().getUniqueId());

        if (matchedMenu != null) {
            matchedMenu.handleClose((org.bukkit.entity.Player) event.getPlayer());
        }

        MenuManager.getInstance().unregisterMenu(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void PlayerQuit(PlayerQuitEvent event) {
        Menu matchedMenu = MenuManager.getInstance().matchMenu(event.getPlayer().getUniqueId());

        if (matchedMenu != null) {
            matchedMenu.handleClose(event.getPlayer());
        }

        MenuManager.getInstance().unregisterMenu(event.getPlayer().getUniqueId());
    }

}
