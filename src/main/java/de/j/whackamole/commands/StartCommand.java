package de.j.whackamole.commands;

import de.j.whackamole.util.Game;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            }
        }
        return false;
    }
}
