package com.kuma.kush.api.player;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static com.kuma.kush.api.text.TextUtil.format;

@Data

public class KPlayer {

    private final Player player;
    private final String name;
    private final String uuid;
    private final int level;
    private final int exp;
    private final double health;
    private final double maxHealth;
    private final Inventory inventory;
    private final Inventory enderChest;


    public KPlayer(Player player) {
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId().toString();
        this.level = player.getLevel();
        this.exp = player.getTotalExperience();
        this.health = player.getHealth();
        this.maxHealth = player.getMaxHealth();
        this.inventory = player.getInventory();
        this.enderChest = player.getEnderChest();
    }

    public void setLevel(int level) {
        player.setLevel(level);
    }

    public void setExp(int exp) {
        player.setTotalExperience(exp);
    }

    public void setHealth(double health) {
        player.setHealth(health);
    }

    public void setMaxHealth(double maxHealth) {
        player.setMaxHealth(maxHealth);
    }

    public void setInventory(Inventory inventory) {
        player.getInventory().setContents(inventory.getContents());
    }

    public void setEnderChest(Inventory enderChest) {
        player.getEnderChest().setContents(enderChest.getContents());
    }

    public void update() {
        player.updateInventory();
    }

    public void save() {
        player.saveData();
    }

    public void kick(String reason) {
        player.kickPlayer(reason);
    }

    public void message(String message) {
        player.sendMessage(format(message));
    }

    public void teleport(Player player) {
        this.player.teleport(player);
    }

    public void teleport(double x, double y, double z) {
        this.player.teleport(new Location(this.player.getWorld(), x, y, z));
    }

    public void teleport(double x, double y, double z, float yaw, float pitch) {
        this.player.teleport(new Location(this.player.getWorld(), x, y, z, yaw, pitch));
    }

    public void teleport(Location location) {
        this.player.teleport(location);
    }


}
