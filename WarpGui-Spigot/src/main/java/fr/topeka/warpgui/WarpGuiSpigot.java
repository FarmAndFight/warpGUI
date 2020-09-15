package fr.topeka.warpgui;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import fr.topeka.warpgui.command.CommandInv;
import fr.topeka.warpgui.inventory.InventoryGui;

public class WarpGuiSpigot extends JavaPlugin{

	public Config config = new Config(this);
	private static WarpGuiSpigot instance;
	private static boolean useBungeecord;
	public HashMap<String, InventoryGui> gui = new HashMap<>();
	
	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		config.load();
		CommandInv commandinv = new CommandInv(this);
		getCommand("warp").setExecutor(commandinv);
		getCommand("lobby").setExecutor(commandinv);
		getServer().getPluginManager().registerEvents(new EventListener(this), this);
		if(useBungeecord) {
			getServer().getMessenger().registerOutgoingPluginChannel( this, "BungeeCord" ); // Register the outgoing channel, to Bungee
		}
	}
	
	public static WarpGuiSpigot getInstance() {
		return instance;
	}

	public boolean getBungeecord() {
		return useBungeecord;
	}
	
	public void setBungeecord(boolean b) {
		useBungeecord = b;
	}
	
}
