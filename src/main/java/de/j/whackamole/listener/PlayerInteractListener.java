package de.j.whackamole.listener;

import de.j.whackamole.commands.StartCommand;
import de.j.whackamole.util.Game;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (!(Objects.requireNonNull(event.getClickedBlock()).getType() == Material.BLACK_SHULKER_BOX)) return;
            event.setCancelled(true);
        } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (StartCommand.running) {
                Game.addScore(event.getPlayer(), - 1);
            }
            event.setCancelled(true);
        }

    }
}
