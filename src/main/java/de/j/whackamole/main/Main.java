package de.j.whackamole.main;

import de.j.whackamole.commands.SpawnPlatformCommand;
import de.j.whackamole.commands.StartCommand;
import de.j.whackamole.listener.BlockListener;
import de.j.whackamole.listener.HitListener;
import de.j.whackamole.listener.PlayerInteractListener;
import de.j.whackamole.listener.PlayerJoinListener;
import de.j.whackamole.util.ScoreboardAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Score;

import java.util.Objects;

public final class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;

        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnPlatformCommand());
        Objects.requireNonNull(getCommand("start")).setExecutor(new StartCommand());

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new HitListener(), this);
        manager.registerEvents(new BlockListener(), this);
        manager.registerEvents(new ScoreboardAPI(), this);
        manager.registerEvents(new PlayerJoinListener(), this);
        manager.registerEvents(new PlayerInteractListener(), this);
    }

    @Override
    public void onDisable() {

    }

    public static Main getPlugin() {
        return plugin;
    }
}
