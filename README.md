
# 🍀 Kush API

[![](https://jitpack.io/v/Xodesito/Kush.svg)](https://jitpack.io/#Xodesito/Kush) 

Kush is an API built on top of PaperMC, designed to streamline various tasks in Minecraft plugin development. It offers convenient functions for creating menus, crafting items, and efficiently managing player interactions. Additionally, Kush supports MiniMessage for formatting in-game messages.

## Features

- Create and manage player interactions seamlessly using KPlayer.
- Build items using ItemBuilder, enabling customization and enchantments.
- Design menus using patterns with the Pattern class and MenuButton for buttons.
- Efficiently handle menu interactions and events.
- Format messages using MiniMessage for rich in-game communication.

## Examples

Creating a KPlayer (Easier Player Handling):

```java
KPlayer player = new KPlayer(event.getPlayer());
player.message("<green> + <white>Welcome to the server!");
```

Creating Items (Using ItemBuilder):
```java
player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD)
        .displayName("<red>GOD SWORD")
        .lore("<rainbow:!>THIS IS RAINBOW?</rainbow>")
        .glow()
        .enchantment(new EnchantmentBuilder().enchant(Enchantment.DAMAGE_ALL, 150))
        .build());
```

Creating Menus:
```java
Pattern pattern = new Pattern(new String[]{
        "####D####",
        "####I####",
        "####E###C",
});

pattern.setSymbol('D', new MenuButton(new ItemBuilder(Material.DIAMOND).displayName("<blue>Diamond").build()));
pattern.setSymbol('I', new MenuButton(new ItemBuilder(Material.IRON_INGOT).displayName("<white>Iron").build()));
pattern.setSymbol('E', new MenuButton(new ItemBuilder(Material.EMERALD).displayName("<green>Emerald").build()));

MenuButton closeButton = new MenuButton(new ItemBuilder(Material.BARRIER).displayName("<red>Close").build());
closeButton.setWhenClicked(clicked -> {
    clicked.closeInventory();
    clicked.sendMessage("<green> + <white>Closed menu!");
});
pattern.setSymbol('C', closeButton);

Menu menu = new Menu(plugin, "<red>Test Menu", 3, pattern);
menu.setInventoryOpened(opened -> {
    KPlayer kPlayer = new KPlayer(opened);
    kPlayer.message("<green> + <white>Opened menu!");
});
menu.setInventoryClosed(closed -> {
    KPlayer kPlayer = new KPlayer(closed);
    kPlayer.message("<green> + <white>Closed menu!");
});
menu.open(player.getPlayer());
```

## ❓ How to add Kush to your project?

### Add the following to your `pom.xml` for Maven:

(replace `LATEST-VERSION` with the latest version (specified at the beginning of the readme))

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.Xodesito</groupId>
    <artifactId>Kush</artifactId>
    <version>LATEST-VERSION</version>
</dependency>
```

### Or for Gradle:
(replace `LATEST-VERSION` with the latest version (specified at the beginning of the readme))

```gradle
repositories {
    ...
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Xodesito:Kush:LATEST-VERSION'
}
```
