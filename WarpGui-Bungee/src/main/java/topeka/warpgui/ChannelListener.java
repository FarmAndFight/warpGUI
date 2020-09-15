package topeka.warpgui;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChannelListener implements Listener {
	
	@EventHandler
	public void onPluginMessage(PluginMessageEvent event) {
		 if (event.getTag().equalsIgnoreCase("BungeeCord")) {
			 DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
			 try {
				String channel = in.readUTF();
				if(channel.equals("_warpGui")) {
					String command = in.readUTF();
					System.out.println("Données reçues: " + command);
					ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), command);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
	}

}
