package de.j.whackamole.util;

import org.bukkit.inventory.ItemStack;

public enum Heads {

    MOLE("NTA3ZmFkNzJiYzJjOGZjNGM5MTRjMmJmMWFjM2MyN2IwMjg5NWVjZjQ3MGI3ZDhjNGViMTk4ODMxOGY5MWFmIn19fQ==", "mole");

    private ItemStack item;
    private String idTag = "";
    private String prefix = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";
    private Heads(String texture, String id) {
        item = ItemBuilder.createHead(prefix + texture, id);
        idTag = id;
    }

    public ItemStack getItemStack() {
        return item;
    }

    public String getName() {
        return idTag;
    }
}
