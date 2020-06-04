package fr.topeka.warpgui.inventory;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InvElement {

	private int position;
	private ItemStack item;
	private String command;
	
	public InvElement(Material material, String title, List<String> description, int position, String command) {
		if(material != null)
			item = new ItemStack(material);
		else
			item = new ItemStack(Material.ANVIL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(title);
		meta.setLore(description);
		item.setItemMeta(meta);
		this.position = position;
		this.command = command;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void execCommand(Player player) {
		Bukkit.dispatchCommand(player, command.replace("{PLAYER}", player.getName()));
	}
}
