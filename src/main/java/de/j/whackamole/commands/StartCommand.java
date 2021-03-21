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
                        switch (sec) {
                            case 60:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 60 Sekunden"));
                                break;
                            case 40:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 40 Sekunden"));
                                break;
                            case 30:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 30 Sekunden"));
                                break;
                            case 20:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 20 Sekunden"));
                                break;
                            case 15:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 15 Sekunden"));
                                break;
                            case 10:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 10 Sekunden"));
                                break;
                            case 9:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 9 Sekunden"));
                                break;
                            case 8:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 8 Sekunden"));
                                break;
                            case 7:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 7 Sekunden"));
                                break;
                            case 6:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 6 Sekunden"));
                                break;
                            case 5:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 5 Sekunden"));
                                break;
                            case 4:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 4 Sekunden"));
                                break;
                            case 3:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 3 Sekunden"));
                                break;
                            case 2:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 2 Sekunden"));
                                break;
                            case 1:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 1 Sekunden"));
                                break;
                            case 0:
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Noch 0 Sekunden"));
                                Bukkit.getScheduler().cancelTask(Game.taskID);
                                player.sendMessage(ChatColor.RED + "Du hast " + Game.getScore(player) + " Punkte erreicht");
                                running = true;
                                start = false;
                                cancel();
                                break;
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
