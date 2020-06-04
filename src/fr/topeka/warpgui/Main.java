package fr.topeka.warpgui;

import org.bukkit.plugin.java.JavaPlugin;

import fr.topeka.warpgui.command.CommandInv;
import fr.topeka.warpgui.inventory.InventoryGui;

public class Main extends JavaPlugin{

	private InventoryGui warp = new InventoryGui(), lobby = new InventoryGui();
	public Config config = new Config(this);
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		config.load();
		CommandInv commandinv = new CommandInv(this);
		getCommand("warp").setExecutor(commandinv);
//		getCommand("warpadmin").setExecutor(null);
		getCommand("lobby").setExecutor(commandinv);
//		getCommand("lobbyadmin").setExecutor(null);
//		getCommand("vote").setExecutor(null);
//		getCommand("voteadmin").setExecutor(null);
		getServer().getPluginManager().registerEvents(new EventListener(this), this);
	}
	
	public InventoryGui getWarp(){
		return warp;
	}
	
	public InventoryGui getLobby(){
		return lobby;
	}
	
	public void setWarp(InventoryGui warp) {
		this.warp = warp;
	}
	
	public void setLobby(InventoryGui lobby) {
		this.lobby = lobby;
	}
	
}
