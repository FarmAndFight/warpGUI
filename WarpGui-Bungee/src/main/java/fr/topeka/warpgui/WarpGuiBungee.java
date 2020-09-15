package fr.topeka.warpgui;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class WarpGuiBungee extends Plugin{

	@Override
	public void onEnable() {
		ProxyServer.getInstance().getPluginManager().registerListener(this, new ChannelListener());
		ProxyServer.getInstance().registerChannel("Return");
	}
	
}
