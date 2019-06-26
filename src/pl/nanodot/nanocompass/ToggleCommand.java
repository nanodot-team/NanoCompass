package pl.nanodot.nanocompass;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class ToggleCommand implements CommandExecutor {

	private NanoCompass plugin;
	private FileConfiguration pluginConfig;
	
	public ToggleCommand(NanoCompass plugin) {
		this.plugin = plugin;
		this.pluginConfig = plugin.getPluginConfig();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("nanocompass")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				// Enable player's compass
				if(args[0].equalsIgnoreCase("wlacz")) {
					
					if(pluginConfig.getStringList("disabled").contains(player.getName())) {
						
						List<String> playersDisabled = pluginConfig.getStringList("disabled");
						playersDisabled.remove(player.getName().toString());
						//plugin.getConfig().getStringList("disabled").remove(player.getName().toString());
						
						pluginConfig.set("disabled", playersDisabled);
						plugin.getLogger().info("Gracz " + player.getName() + " wlaczyl kompas!");
						player.sendMessage(ChatColor.GREEN + "W��czono kompas.");
						
					} else {
						
						player.sendMessage(ChatColor.RED + "Kompas jest ju� w��czony!");
						
					}
					
				// Disable player's compass
				} else if (args[0].equalsIgnoreCase("wylacz")) {
					
					if(!pluginConfig.getStringList("disabled").contains(player.getName())) {
						
						List<String> playersDisabled = pluginConfig.getStringList("disabled");
						playersDisabled.add(player.getName().toString());
						
						pluginConfig.set("disabled", playersDisabled);
						plugin.getLogger().info("Gracz " + player.getName() + " wylaczyl kompas!");
						player.sendMessage(ChatColor.GREEN + "Wy��czono kompas.");
						
					} else {
						player.sendMessage(ChatColor.RED + "Kompas jest ju� wy��czony!");
					}
					
				} else {
					
					player.sendMessage(ChatColor.GREEN + "Poprawne u�ycie: /nanocompass [wlacz|wylacz]");
					
				}
				
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Komendy mog� u�ywa� tylko gracze!");
			}
 		}
		
		return false;
	}

}