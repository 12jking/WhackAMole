package de.j.whackamole.commands;

import de.j.whackamole.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SpawnPlatformCommand implements CommandExecutor {

    public static ArrayList<Location> locations= new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("whack.spawn")) {
                try {
                    spawnPlatform(player.getLocation());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                player.sendMessage(ChatColor.RED + "Dazu hast du keine Rechte!");
        }
        return false;
    }

    private void spawnPlatform(Location location) throws IOException {
        if (!new File("plugins//WhackAMole//location.yml").exists()) {
            try {
                new File("plugins//WhackAMole//location.yml").createNewFile();
            } catch (IOException e) {
                Main.getPlugin().getLogger().severe("An error appeared while saving score");
            }
        }
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(new File("//plugins//WhackAMole//location.yml"));
        final String CONFIG_PREFIX = "whackamole.location.";

        for (int x = 0; x < 10; x++) {
            for (int z = 0; z < 10; z++) {
                new Location(location.getWorld(), (int) location.getX() + x, (int) location.getY(), (int) location.getZ() + z).getBlock().setType(Material.BLACK_SHULKER_BOX);
                locations.add(new Location(location.getWorld(), (int) location.getX() + x, (int) location.getY(), (int) location.getZ() + z));
            }
        }
        int current = 1;
        for (Location currentLocation : locations) {
            configuration.set(CONFIG_PREFIX + current, currentLocation);
            current ++;
        }

        configuration.save("plugins//WhackAMole//location.yml");
    }

    public static void init() {
        try {
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(new File("plugins//WhackAMole//location.yml"));
            for (int i = 1; i <= 100; i++) {
                locations.add((Location) configuration.get("whackamole.location." + i));
            }
        } catch (ClassCastException | NullPointerException e) {
            Main.getPlugin().getLogger().severe("An error appeared while loading locations from config.yml");
        }
    }
}