package topeka.warpgui;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;


/**
 * https://www.spigotmc.org/threads/sending-data-from-spigot-to-bungeecord.302447/
 *
 */
public class BungeeChannel{

	
	private static BungeeChannel instance;

	
	public void execCommandBungee(Player player, String command) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("_warpGui");
		out.writeUTF(command);
		player.sendPluginMessage(WarpGuiSpigot.getInstance(), "BungeeCord", out.toByteArray());
	}
	
	
	public static BungeeChannel getInstance() {
        if( instance == null ){
            synchronized(BungeeChannel.class) {
                if(instance == null){
                    instance = new BungeeChannel();
                }
            }
        }
        return instance;
    }
	
}
