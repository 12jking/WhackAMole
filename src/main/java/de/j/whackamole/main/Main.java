package de.j.whackamole.main;

import de.j.whackamole.commands.SpawnPlatformCommand;
import de.j.whackamole.commands.StartCommand;
import de.j.whackamole.listener.BlockListener;
import de.j.whackamole.listener.HitListener;
import de.j.whackamole.listener.PlayerJoinListener;
import de.j.whackamole.util.ScoreboardAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Score;

public final class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getCommand("spawn").setExecutor(new SpawnPlatformCommand());
        getCommand("start").setExecutor(new StartCommand());

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new HitListener(), this);
        manager.registerEvents(new BlockListener(), this);
        manager.registerEvents(new ScoreboardAPI(), this);
        manager.registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable() {

    }

    public static Main getPlugin() {
        return plugin;
    }
}
