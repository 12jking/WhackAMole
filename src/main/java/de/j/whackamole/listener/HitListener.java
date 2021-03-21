package de.j.whackamole.listener;

import de.j.whackamole.commands.StartCommand;
import de.j.whackamole.main.Main;
import de.j.whackamole.util.Game;
import jdk.javadoc.internal.tool.Start;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class HitListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        if (!(event.getEntity() instanceof ArmorStand)) return;

        if (StartCommand.running) {
            Game.addScore(player, getPoints(event.getEntity()));
        }

        event.setCancelled(true);
        event.getEntity().remove();

        Main.getPlugin().getLogger().info("+1");
    }

    private int getPoints(Entity entity) {
        int ticksLive = entity.getTicksLived();
        if (ticksLive < 10) {
            return 4;
        } else if (ticksLive < 15) {
            return 3;
        } else if (ticksLive < 20) {
            return 2;
        } else if (ticksLive < 25) {
            return 1;
        }
        return 0;
    }
}
