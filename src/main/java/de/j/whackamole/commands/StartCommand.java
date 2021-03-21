package de.j.whackamole.commands;

import de.j.whackamole.main.Main;
import de.j.whackamole.util.Game;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartCommand implements CommandExecutor {

    public static boolean start = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            start = !start;
            player.sendMessage(ChatColor.GREEN + "Start: " + start);

            if (start) {
                Game.spawnEntity();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.sendMessage(ChatColor.RED + "Du hast " + Game.getScore(player) + " Punkte erreicht!");
                        Bukkit.getScheduler().cancelTask(Game.taskID);
                    }
                }.runTaskLater(Main.getPlugin(), 20*60);
            } else
                Bukkit.getScheduler().cancelTask(Game.taskID);
        }
        return false;
    }
}
