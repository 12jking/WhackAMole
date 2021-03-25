package de.j.whackamole.util;

import de.j.whackamole.commands.SpawnPlatformCommand;
import de.j.whackamole.commands.StartCommand;
import de.j.whackamole.main.Main;
import de.j.whackamole.util.events.ScoreChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class Game implements Listener {

    private static HashMap<Player, Integer> score = new HashMap<>();
    public static int taskID;

    public static void addScore(Player player, int points) {
        if (!score.containsKey(player)) {
            score.put(player, points);
        }else
            score.put(player, score.get(player) + points);

        Bukkit.getPluginManager().callEvent(new ScoreChangeEvent(player, score.get(player)));
    }

    public static void spawnEntity() {
        if (SpawnPlatformCommand.locations.isEmpty())
            SpawnPlatformCommand.init();

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            int y = 500;

            @Override
            public void run() {
                Location location = SpawnPlatformCommand.locations.get(new Random().nextInt(SpawnPlatformCommand.locations.size()));
                if (y == 500) {
                    y = (int) location.getY() - 1;
                }
                location.setY(y);

                ArmorStand entity = (ArmorStand) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ARMOR_STAND);

                entity.setGravity(false);

                entity.setInvisible(true);
                Objects.requireNonNull(entity.getEquipment()).setHelmet(ItemBuilder.getHead("mole"));

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        entity.setVelocity(new Vector(0, 0.389, 0));
                        entity.setGravity(true);
                    }
                }.runTaskLater(Main.getPlugin(), 5);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        entity.remove();
                        if (!StartCommand.start) {
                            cancel();
                        }
                    }
                }.runTaskLaterAsynchronously(Main.getPlugin(), 30);
                if (!StartCommand.start) {
                    Bukkit.getScheduler().cancelTask(taskID);
                }
            }
        },0, 3);

    }

    public static int getScore(Player player) {
        try {
            return score.get(player);
        } catch (NullPointerException e) {
            return -1;
        }
    }

    public static void saveScore(Player player) {
        Thread thread = new Thread(() -> {
            if (!new File("plugins//WhackAMole//scores.yml").exists()) {
                try {
                    new File("plugins//WhackAMole//scores.yml").createNewFile();
                } catch (IOException e) {
                    Main.getPlugin().getLogger().severe("An error appeared while saving score");
                }
            }
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(new File("plugins//WhackAMole//scores.yml"));
            if (configuration.get("whackamole.scores." + player.getName()) != null) {
                if (configuration.getInt("whackamole.scores." + player.getName()) < getScore(player)) {
                    configuration.set("whackamole.scores." + player.getName(), getScore(player));
                    player.sendMessage(ChatColor.DARK_RED + "Du hast einen neuen Highscore erreicht!");
                    try {
                        configuration.save(new File("plugins//WhackAMole//scores.yml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                        Main.getPlugin().getLogger().severe("An error appeared while saving score");
                    }
                } else
                    player.sendMessage(ChatColor.DARK_RED + "Du hast deinen Highscore nicht erreicht");
            } else {
                try {
                    configuration.set("whackamole.scores." + player.getName(), getScore(player));
                    player.sendMessage(ChatColor.DARK_RED + "Du hast einen neuen Highscore erreicht!");
                    configuration.save(new File("plugins//WhackAMole//scores.yml"));
                } catch (IOException e) {
                    e.printStackTrace();
                    Main.getPlugin().getLogger().severe("An error appeared while saving score");
                }
            }

        });
        thread.start();

    }
}