package fr.waycoo.dropchest.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.waycoo.dropchest.Core;
import fr.waycoo.dropchest.chest.ChestManager;
import fr.waycoo.dropchest.config.Config;
import net.md_5.bungee.api.ChatColor;

public class CommandDropchest implements CommandExecutor{
	private Core core;
	private String command;
	private ChestManager chest;

	
	public CommandDropchest(Core core, String command) {
		this.core = core;
		this.command = command;
		core.getCommand(command).setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) return true;
		
		if(label.equalsIgnoreCase("dropchest")) {
			if(args.length == 0 && args.length >= 2) return true;

			if(args.length == 1 ) {
				if(args[0].equalsIgnoreCase("launch")) {
					this.chest = new ChestManager(core);
					this.chest.createChest();

					Bukkit.broadcastMessage(ChatColor.GOLD +  "Un coffre a apparu en: "  +
							ChatColor.GOLD + " x: " + ChatColor.YELLOW + this.chest.getLocationChestX() +
							ChatColor.GOLD + " y: " + ChatColor.YELLOW + this.chest.getLocationChestY() +
							ChatColor.GOLD + " z: " + ChatColor.YELLOW + this.chest.getLocationChestZ());
					return true;
				}
			}
		}
		return false;
	}
}
