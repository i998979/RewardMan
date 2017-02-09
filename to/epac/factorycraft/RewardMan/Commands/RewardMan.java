package to.epac.factorycraft.RewardMan.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import to.epac.factorycraft.RewardMan.Main;
import to.epac.factorycraft.RewardMan.Utils.GetNearestEntityInSight;

public class RewardMan implements CommandExecutor {
	
	public void HelpPage(CommandSender sender){
		sender.sendMessage(ChatColor.YELLOW + "/rwm create: " + ChatColor.BLUE + "Create a new RewardMan");
		sender.sendMessage(ChatColor.YELLOW + "/rwm remove [id]: " + ChatColor.BLUE + "Remove a RewardMan");
		sender.sendMessage(ChatColor.YELLOW + "/rwm list: " + ChatColor.BLUE + "List all RewardMan");
		sender.sendMessage(ChatColor.YELLOW + "/rwm resetplayer: " + ChatColor.BLUE + "Reset all player's data");
		sender.sendMessage(ChatColor.YELLOW + "/rwm resetall: " + ChatColor.BLUE + "Reset all data");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		
		if (args[0].equalsIgnoreCase("create")){
			String name = "";
			
			for (int i = 1; i < args.length; i++){
				name += args[i];
				if (i + 1 < args.length)
					name += " ";
			}
			name = ChatColor.translateAlternateColorCodes('&', name);
			
			NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);
			npc.data().setPersistent("player-skin-name", "Zealock");
			
			npc.spawn(player.getLocation());
			
			int id = npc.getId();
			//Add RewardMan to data.yml / HashMap
			player.sendMessage(ChatColor.YELLOW + "RewardMan " + id + " with name " + name + ChatColor.YELLOW + " has been created.");
			
			Hologram hologram = HologramsAPI.createHologram(Main.plugin, player.getLocation().add(0, 3, 0));
			hologram.insertTextLine(0, ChatColor.RED + "" + ChatColor.BOLD + "RIGHT CLICK");
			hologram.insertTextLine(0, ChatColor.YELLOW + "Claim your reward here");
		}
		else if (args[0].equalsIgnoreCase("remove")){
			Entity target = GetNearestEntityInSight.getNearestEntityInSight(player, 20);
			NPC npc = CitizensAPI.getNPCRegistry().getNPC(target);
			
			if (target != null && npc != null){
				npc.destroy();
				//Remove RewardMan from data.yml / HashMap
				//Remove hologram
				player.sendMessage(ChatColor.GREEN + "RewardMan " + npc.getId() + " has been removed.");
			} else {
				player.sendMessage(ChatColor.RED + "You are not targeting a RewardMan.");
			}
		}
		else if (args[0].equalsIgnoreCase("list")){
			
		}
		else if (args[0].equalsIgnoreCase("resetplayer")){
			
		}
		else if (args[0].equalsIgnoreCase("resetall")){
			
		}
		else if (args.length==0){
			HelpPage(player);
		}
		else {
			HelpPage(player);
			
		}
		
		return false;
	}
}
