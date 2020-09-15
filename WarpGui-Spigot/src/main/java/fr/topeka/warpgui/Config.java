package fr.topeka.warpgui;


import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import fr.topeka.warpgui.inventory.InvElement;
import fr.topeka.warpgui.inventory.InventoryGui;

public class Config {

	private WarpGuiSpigot main;

	public Config(WarpGuiSpigot main) {
		this.main = main;
	}

	public void load() {
		FileConfiguration file = main.getConfig();
		loadGui("warp", file);
		loadGui("lobby", file);
	}
	
	private void loadGui(String label, FileConfiguration file) {
		String command = Character.toUpperCase(label.charAt(0)) + label.substring(1);
		InventoryGui inv = new InventoryGui(command);
		int i = 0;
		while(file.contains(label + "." + i)) {
			inv.getElements().add(new InvElement(Material.getMaterial(file.getString(label + "." + i + ".Material")), file.getString(label + "." + i + ".Title"), file.getStringList(label + "." + i + ".Description"), file.getInt(label + "." + i + ".Position"), file.getStringList(label + "." + i + ".Command")));
			i++;
		}
		inv.createInventory();
		WarpGuiSpigot.getInstance().gui.put(label, inv);
	}
	
}
