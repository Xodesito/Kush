package com.kuma.kush.api.text;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TextUtil {

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private static final LegacyComponentSerializer serializer = LegacyComponentSerializer.builder().hexColors().useUnusualXRepeatedCharacterHexFormat().build();

    public static String format(String text) {
        return serializer.serialize(miniMessage.deserialize(text));
    }

    public static List<String> format(List<String> text) {
        for (int i = 0; i < text.size(); i++) {
            text.set(i, serializer.serialize(miniMessage.deserialize(text.get(i))));
        }
        return text;
    }

    public static String[] format(String... text) {
        for (int i = 0; i < text.length; i++) {
            text[i] = serializer.serialize(miniMessage.deserialize(text[i]));
        }
        return text;
    }

    public static List<String> formatLore(List<String> lore) {
        for (int i = 0; i < lore.size(); i++) {
            lore.set(i, serializer.serialize(miniMessage.deserialize(lore.get(i))));
        }
        return lore;
    }

    public static @Nullable List<String> formatLore(String... lore) {
        for (int i = 0; i < lore.length; i++) {
            lore[i] = serializer.serialize(miniMessage.deserialize(lore[i]));
        }
        return List.of(lore);
    }

}
