package fr.topeka.warpgui.command;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.topeka.warpgui.WarpGuiSpigot;
import fr.topeka.warpgui.inventory.InventoryGui;

public class CommandInv implements CommandExecutor {

	private WarpGuiSpigot main;

	public CommandInv(WarpGuiSpigot main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 1 && args[0].toUpperCase().equals("RELOAD") && sender.hasPermission("warpgui.warp.reload")) {
			main.reloadConfig();
			main.config.load();
			sender.sendMessage("Successfully reloaded");
			return true;
		}
		if(sender instanceof Player) {
			Player player = (Player) sender;
			String command = Character.toUpperCase(label.charAt(0)) + label.substring(1);
			try {
				InventoryGui inv = (InventoryGui) main.getClass().getMethod("get" + command).invoke(main);
				inv.displayInventory(player);
				return true;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				return false;
			}
		}
		sender.sendMessage("This is a player-only command");
		return true;
	}
}
