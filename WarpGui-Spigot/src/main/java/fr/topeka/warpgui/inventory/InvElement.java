package fr.topeka.warpgui.inventory;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.topeka.warpgui.BungeeChannel;
import fr.topeka.warpgui.WarpGuiSpigot;

public class InvElement {

	private int position;
	private ItemStack item;
	private List<String> command;
	
	public InvElement(Material material, String title, List<String> description, int position, List<String> list) {
		if(material != null)
			item = new ItemStack(material);
		else
			item = new ItemStack(Material.ANVIL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(title);
		meta.setLore(description);
		item.setItemMeta(meta);
		this.position = position;
		this.command = list;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void execCommand(Player player) {
		for(String s : command) {
			s = s.replace("{PLAYER}", player.getName());
			if(WarpGuiSpigot.getInstance().getBungeecord() && s.startsWith("\\[BungeeCord]")) {
				BungeeChannel.getInstance().execCommandBungee(player, s.substring(13));
			}else if(WarpGuiSpigot.getInstance().getBungeecord() && s.startsWith("\\[Server]")) {
				BungeeChannel.getInstance().changePlayerServer(player, s.substring(9));
			}else {
				Bukkit.dispatchCommand(player, s);
			}
		}
	}
}
