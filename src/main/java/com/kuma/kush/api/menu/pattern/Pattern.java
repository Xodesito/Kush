package com.kuma.kush.api.menu.pattern;

import com.kuma.kush.api.menu.Menu;
import com.kuma.kush.api.menu.button.MenuButton;

import java.util.HashMap;
import java.util.Map;

public class Pattern {

    private final String[] pattern;
    private final Map<Character, MenuButton> charMap;

    public Pattern(String[] pattern) {
        this.pattern = pattern;
        this.charMap = new HashMap<>();
    }

    public void setSymbol(char symbol, MenuButton menuButton) {
        charMap.put(symbol, menuButton);
    }

    public void applyToMenu(Menu menu) {
        for (int row = 0; row < pattern.length; row++) {
            String patternRow = pattern[row];
            for (int col = 0; col < patternRow.length(); col++) {
                char symbol = patternRow.charAt(col);
                int slot = col + (row * 9);

                if (charMap.containsKey(symbol)) {
                    MenuButton menuButton = charMap.get(symbol);
                    menu.registerButton(menuButton, slot);
                }
            }
        }
    }
}
