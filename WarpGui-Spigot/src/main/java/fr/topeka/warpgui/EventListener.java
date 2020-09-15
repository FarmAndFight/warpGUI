package fr.topeka.warpgui;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.topeka.warpgui.inventory.InvElement;

public class EventListener implements Listener {

	private WarpGuiSpigot main;

	public EventListener(WarpGuiSpigot main) {
		this.main = main;
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onInventoryClickEvent(InventoryClickEvent event) {
		if(event.isLeftClick()) {
			int slot = event.getSlot();
			String title = event.getView().getTitle();
			if(title.equals("Warp") || title.equals("Lobby")) {
				InvElement element = main.gui.get(title.toLowerCase()).getClickedItem(slot);
				if(element != null) {
					if(event.getWhoClicked() instanceof Player) {
						event.setCancelled(true);
						element.execCommand((Player) event.getWhoClicked());
					}
				}
			}
		}
	}

}
