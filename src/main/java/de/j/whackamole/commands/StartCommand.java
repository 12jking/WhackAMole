package de.j.whackamole.commands;

import de.j.whackamole.main.Main;
import de.j.whackamole.util.Game;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartCommand implements CommandExecutor {

    public static boolean start = false;
    public static boolean running = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            start = !start;
            player.sendMessage(ChatColor.GREEN + "Start: " + start);

            if (start) {
                running = true;
                Game.spawnEntity();
                new BukkitRunnable() {
                    int sec = 60;
                    @Override
                    public void run() {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch " + sec + " Sekunden"));
                        if (sec == 0) {
                            player.sendMessage(ChatColor.RED + "Du hast " + Game.getScore(player) + " Punkte erreicht");
                            Bukkit.getScheduler().cancelTask(Game.taskID);
                            running = true;
                            start = false;
                            Game.saveScore(player);
                            cancel();
                        }
                        sec --;
                    }
                }.runTaskTimer(Main.getPlugin(), 0, 20);
            } else
                Bukkit.getScheduler().cancelTask(Game.taskID);
        }
        return false;
    }
}