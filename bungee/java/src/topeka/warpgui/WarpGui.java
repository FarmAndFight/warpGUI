package topeka.warpgui;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;

public class WarpGui extends Plugin{

	@Override
	public void onEnable() {
		BungeeCord.getInstance().getPluginManager().registerListener(this, new ChannelListener());
        BungeeCord.getInstance().registerChannel("Return");
	}
	
}
