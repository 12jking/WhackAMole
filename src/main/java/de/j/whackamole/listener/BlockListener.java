package de.j.whackamole.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if (!(event.getBlock().getType() == Material.BLACK_SHULKER_BOX)) return;
        event.setCancelled(true);
    }
}
