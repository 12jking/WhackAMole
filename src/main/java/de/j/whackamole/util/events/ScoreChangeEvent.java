package de.j.whackamole.util.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ScoreChangeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private int score;

    public ScoreChangeEvent(Player player, int score) {
        this.player = player;
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
