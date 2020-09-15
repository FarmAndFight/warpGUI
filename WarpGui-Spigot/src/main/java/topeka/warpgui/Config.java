package topeka.warpgui;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import topeka.warpgui.inventory.InvElement;
import topeka.warpgui.inventory.InventoryGui;

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
		try {
			InventoryGui inv = new InventoryGui(label);
//			InventoryGui inv = (InventoryGui) main.getClass().getMethod("get" + command).invoke(main);
			int i = 0;
//			inv.getElements().clear();
			while(file.contains(label + "." + i)) {
				inv.getElements().add(new InvElement(Material.getMaterial(file.getString(label + "." + i + ".Material")), file.getString(label + "." + i + ".Title"), file.getStringList(label + "." + i + ".Description"), file.getInt(label + "." + i + ".Position"), file.getStringList(label + "." + i + ".Command")));
				i++;
			}
			inv.createInventory();
			main.getClass().getMethod("set" + command, InventoryGui.class).invoke(main, inv);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		saveGui("warp");
		saveGui("lobby");
	}
	
	private void saveGui(String label) {
		
	}
	
}
