package topeka.warpgui;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import topeka.warpgui.inventory.InvElement;
import topeka.warpgui.inventory.InventoryGui;

public class EventListener implements Listener {

	private Main main;

	public EventListener(Main main) {
		this.main = main;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onInventoryClickEvent(InventoryClickEvent event) {
		if(event.isLeftClick()) {
			int slot = event.getSlot();
			String title = Character.toUpperCase(event.getView().getTitle().charAt(0)) + event.getView().getTitle().substring(1);
			if(title.equalsIgnoreCase("warp") || title.equalsIgnoreCase("Lobby")) {
				try {
					InvElement element = ((InventoryGui) main.getClass().getMethod("get" + title).invoke(main)).getClickedItem(slot);
					if(element != null) {
						event.setCancelled(true);
						if(event.getWhoClicked() instanceof Player) {
							element.execCommand((Player) event.getWhoClicked());
						}
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
