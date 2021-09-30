package fr.waycoo.dropchest.listener;

import java.util.HashMap;
import java.util.Map;

import fr.waycoo.dropchest.chest.ChestManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.waycoo.dropchest.Core;
import net.md_5.bungee.api.ChatColor;

public class ChestRunnable implements Listener {
	private Core core;
	private Map<Player,Block> openedList = new HashMap<>();
	private ConfigurationSection openChest;
	
	public ChestRunnable(Core core) {
		this.core = core;
	}

	@EventHandler
	public void ChestOpen(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		Player player = event.getPlayer();

		this.openChest = core.getConfiguration().getConfigurationSection("OpenChest");
		int timeDispawn = openChest.getInt("TimeDispawn");
		String replace = openChest.getString("Replace");
		boolean chestDispawn = openChest.getBoolean("ChestDispawn");

		Material replace1 = Material.getMaterial(replace);

		if(event.getAction() != Action.RIGHT_CLICK_BLOCK || !event.hasBlock()) return;
		if(openedList.containsKey(player)) return;

		if(chestDispawn) {
			if (block.getType() == Material.CHEST) {
				openedList.put(player, block);
				Bukkit.getScheduler().runTaskTimer(core, new Runnable() {

					int timer = timeDispawn;

					@Override
					public void run() {

						if (block.getType() == Material.AIR) {
							timer = 0;
							Bukkit.getScheduler().cancelAllTasks();
						}

						if (timer == 0) {
							block.setType(replace1);
							openedList.remove(player, block);
							Bukkit.broadcastMessage(ChatColor.GOLD + "Le coffre a été ouvert par: " + ChatColor.YELLOW + player.getName());
							Bukkit.getScheduler().cancelAllTasks();
						}
						System.out.println("time: " + timer);
						timer--;
					}
				}, 0, 20);
			}
		}
	}
}
