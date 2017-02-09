package to.epac.factorycraft.RewardMan;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import to.epac.factorycraft.RewardMan.Commands.RewardMan;

public class Main extends JavaPlugin{
	public static Plugin plugin;
	
	public void onEnable(){
		plugin = this;
		getCommand("rewardman").setExecutor(new RewardMan());
	}
	
	public void onDisable(){
		
	}
}
