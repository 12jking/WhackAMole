package de.j.whackamole.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (!(Objects.requireNonNull(event.getClickedBlock()).getType() == Material.BLACK_SHULKER_BOX)) return;
        event.setCancelled(true);
    }
}
