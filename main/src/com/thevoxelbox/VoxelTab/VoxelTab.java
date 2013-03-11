package com.thevoxelbox.VoxelTab;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
/*
 * @Author Drew
 * 
 * 
 * 
 * @TheVoxelBox
 */
public class VoxelTab extends JavaPlugin
  implements Listener
{
@Override
public void onEnable()
  {
    getServer().getPluginManager().registerEvents(this, this);
    getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
    {
@Override
public void run() {
        for (Player player : VoxelTab.this.getServer().getOnlinePlayers())
          VoxelTab.this.updateName(player);
      }
    }
    , 0L, 200L);
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    updateName(event.getPlayer());
  }
  public void loadFactorySettings() {
	  getConfig().getString("reset", "no");
	    log("| ========================================== |");
	    log("| * VoxelTab                             |");
	    log("| *                                          |");
	    log("| * Basic Tab Based coloring plugin  |");
	    log("| *                                          |");
	    log("| * Drew and VoxelPlugineering    |");
	    log("| ========================================== |");
	    log("Factory settings loaded");
	  }
  
  public static void log(String str) {
    log(str);
  }

  public void updateName(Player player) {
    String name = player.getName();
    for (ChatColor color : ChatColor.values()) {
      String permission = "VoxelTab." + color.getChar();
      if (player.hasPermission(permission)) {
        if (name.length() + 4 > 15)
          name = color + name.substring(0, Math.min(name.length() + 6, 10)) + "¤f";
        else {
          name = color + name + "¤f";
        }
        player.setPlayerListName(name);
        return;
      }
    }
  }
}