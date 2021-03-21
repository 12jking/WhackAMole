package de.j.whackamole.util;

import de.j.whackamole.commands.SpawnPlatformCommand;
import de.j.whackamole.commands.StartCommand;
import de.j.whackamole.main.Main;
import de.j.whackamole.util.events.ScoreChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

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
        if (!SpawnPlatformCommand.locations.isEmpty()) {
            /*new BukkitRunnable() {
                int y = 500;
                @Override
                public void run() {
                    Location location = SpawnPlatformCommand.locations.get(new Random().nextInt(SpawnPlatformCommand.locations.size()));
                    if (y == 500) {
                        y = (int) location.getY() - 1;
                    }
                    location.setY(y);

                    ArmorStand entity = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

                    entity.setGravity(false);

                    entity.setInvisible(true);
                    entity.getEquipment().setHelmet(ItemBuilder.getHead("mole"));

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
                        cancel();
                    }
                }
            }.runTaskTimer(Main.getPlugin(), 0, 5);*/

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
            },0, 5);
        }
    }

    public static int getScore(Player player) {
        return score.get(player);
    }

}
