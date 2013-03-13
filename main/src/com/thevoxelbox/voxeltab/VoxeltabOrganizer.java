package com.thevoxelbox.VoxelTab

public class VoxeltabOrganizer {

/*
 * @Author Drew
 * 
 * Not Sure if this is Staying Yet Need to test more
 * 
 * @TheVoxelBox	
 */
	
	import java.io.File;
	import java.io.PrintStream;
	import java.util.ArrayList;
	import net.minecraft.server.v1_4_R1.EntityPlayer;
	import net.minecraft.server.v1_4_R1.Packet201PlayerInfo;
	import net.minecraft.server.v1_4_R1.PlayerConnection;
	import org.bukkit.Bukkit;
	import org.bukkit.Server;
	import org.bukkit.configuration.file.FileConfiguration;
	import org.bukkit.configuration.file.FileConfigurationOptions;
	import org.bukkit.craftbukkit.v1_4_R1.entity.CraftPlayer;
	import org.bukkit.entity.Player;
	import org.bukkit.event.EventHandler;
	import org.bukkit.event.Listener;
	import org.bukkit.event.player.PlayerJoinEvent;
	import org.bukkit.plugin.PluginManager;
	import org.bukkit.plugin.java.JavaPlugin;

	public class SuperTab extends JavaPlugin
	  implements Listener
	{
	  private ArrayList<String> messageen = new ArrayList();

	  public void loadConfig()
	  {
	    if (new File("plugins/VoxelTab/config.yml").exists())
	    {
	      FileConfiguration config = getConfig();
	      config.options().copyDefaults(true);
	    }
	    else {
	      saveDefaultConfig();
	      System.out.println("VoxelTab Config Loaded!");
	    }
	  }

	  public static String shortenString(String message)
	  {
	    int length = message.length();

	    if (length == 15)
	    {
	      message = " " + message;
	    }

	    if (length == 14)
	    {
	      message = "  " + message;
	    }

	    if (length == 13)
	    {
	      message = "   " + message;
	    }

	    if (length == 12)
	    {
	      message = "    " + message;
	    }

	    if (length == 11)
	    {
	      message = "     " + message;
	    }

	    if (length == 10)
	    {
	      message = "      " + message;
	    }

	    if (length == 9)
	    {
	      message = "       " + message;
	    }

	    if (length == 8)
	    {
	      message = "        " + message;
	    }

	    if (length == 7)
	    {
	      message = "         " + message;
	    }

	    if (length == 6)
	    {
	      message = "          " + message;
	    }

	    if (length == 5)
	    {
	      message = "           " + message;
	    }

	    if (length == 4)
	    {
	      message = "            " + message;
	    }

	    if (length == 3)
	    {
	      message = "             " + message;
	    }

	    if (length == 2)
	    {
	      message = "              " + message;
	    }

	    if (length == 1)
	    {
	      message = "               " + message;
	    }

	    if (length > 16)
	    {
	      message = message.substring(0, 13) + "...";
	    }

	    return message;
	  }

	  public static void setTab(ArrayList<String> contents)
	  {
	    for (Player t : Bukkit.getServer().getOnlinePlayers())
	    {
	      CraftPlayer cp = (CraftPlayer)t;
	      EntityPlayer nmsPlayer = cp.getHandle();

	      for (String message : contents)
	      {
	        message = shortenString(message);

	        String nmessage = message.replace("%playername%", t.getName()).replace("%onlineplayers%", String.valueOf(Bukkit.getServer().getOnlinePlayers().length))
	          .replace("%slots%", String.valueOf(Bukkit.getServer().getMaxPlayers())).replace("%motd%", Bukkit.getServer().getMotd()).replaceAll("(?i)&([a-f0-9])", "¤$1")
	          .replace("%healt%", String.valueOf(t.getHealth())).replace("%level%", String.valueOf(t.getLevel())).replace("%ip%", String.valueOf(t.getAddress()));
	        nmsPlayer.playerConnection.sendPacket(new Packet201PlayerInfo(nmessage, true, 9999));
	      }
	    }
	  }

	  @EventHandler
	  public void onJoin(PlayerJoinEvent event)
	  {
	    if (!getConfig().getBoolean("UseVoxelTab"))
	    {
	      return;
	    }

	    ArrayList sl = (ArrayList)getConfig().getList("TabConfig");

	    this.messageen.clear();

	    for (String message : sl)
	    {
	      this.messageen.add(message);
	    }

	    setTab(sl);
	  }
	}