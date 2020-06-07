package topeka.warpgui;

import org.bukkit.plugin.java.JavaPlugin;

import topeka.warpgui.command.CommandInv;
import topeka.warpgui.inventory.InventoryGui;

public class Main extends JavaPlugin{

	private InventoryGui warp = new InventoryGui("warp"), lobby = new InventoryGui("lobby");
	public Config config = new Config(this);
	private static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
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
        getServer().getMessenger().registerOutgoingPluginChannel( this, "BungeeCord" ); // Register the outgoing channel, to Bungee
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
	
	public static Main getInstance() {
		return instance;
	}
	
}
