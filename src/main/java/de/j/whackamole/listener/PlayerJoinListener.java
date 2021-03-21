package de.j.whackamole.listener;

import de.j.whackamole.util.Game;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Game.addScore(event.getPlayer(), 0);
    }
}
