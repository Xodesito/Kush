package com.kuma.kush.api.menu.manager;

import com.kuma.kush.api.menu.Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MenuManager {

    private static MenuManager instance;
    private final Map<UUID, Menu> openMenus;

    public static MenuManager getInstance() {
        if (instance == null) {
            instance = new MenuManager();
        }
        return instance;
    }

    public MenuManager() {
        this.openMenus = new HashMap<>();
    }

    public void registerMenu(UUID toRegister, Menu menu) {
        openMenus.put(toRegister, menu);
    }

    public void unregisterMenu(UUID toUnregister) {
        openMenus.remove(toUnregister);
    }

    public Menu matchMenu(UUID user) {
        return openMenus.get(user);
    }
}
