package fr.topeka.warpgui;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
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
					String choice = in.readUTF();
					ProxiedPlayer player = ProxyServer.getInstance().getPlayer(in.readUTF());
					String command = in.readUTF();
					switch(choice) {
					case "SERVER":
						System.out.println("sending " + player.getName() + " to " + command);
						ServerInfo server = ProxyServer.getInstance().getServers().get(command);
						if(server != null) {
							player.connect(server);	
						}else {
							ProxyServer.getInstance().getLogger().warning("Server " + command + "doesn't exist");
							player.sendMessage(new TextComponent("Can't send you to server " + command + ": server doesn't exist"));
						}
						break;
					case "COMMAND":default:
						ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), command);
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
	}

}
