package de.j.whackamole.util;

import de.j.whackamole.util.events.ScoreChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public class ScoreboardAPI implements Listener {

    private static Scoreboard scoreboard;
    private static Objective objective;

    @EventHandler
    public void onScoreChange(ScoreChangeEvent event) {
        if (scoreboard == null)
            init();

        objective.getScore(ChatColor.DARK_AQUA + event.getPlayer().getName()).setScore(event.getScore());
        setScoreboard(event.getPlayer());
    }

    public static void setScoreboard(Player player) {
        player.setScoreboard(scoreboard);
    }

    public static void init() {
        scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        objective = scoreboard.registerNewObjective("abcd", "abcd", "abcd");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.GOLD + "Score");
    }

}
