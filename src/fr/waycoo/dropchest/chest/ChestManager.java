package fr.waycoo.dropchest.chest;

import fr.waycoo.dropchest.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;

import java.util.Random;

public class ChestManager {
	private Core core;
	private Location loc;
	private Chest chest;
	private ConfigurationSection worldName, worldNameMax, itemsSection, numberSection;

	public ChestManager(Core core) {
		this.core = core;
	}

	public void createChest() {
		this.worldName = core.getConfiguration().getConfigurationSection("CenterWorld");
		this.itemsSection = core.getConfiguration().getConfigurationSection("Items");

		this.locationChest(core.getConfiguration().getString("World"), this.worldName.getInt("x"), this.worldName.getInt("z"));

		this.itemsSection.getKeys(false)
				.forEach(key -> {
					this.numberSection = itemsSection.getConfigurationSection(key);
					String type = numberSection.getString("type");
					String enchantment = numberSection.getString("enchantment");
					int quantity = numberSection.getInt("quantity");
					int level = numberSection.getInt("level");

					Material type1 = Material.getMaterial(type);

					int slot = new Random().nextInt(chest.getInventory().getSize());

					if(numberSection.getString("enchantment") == null || numberSection.getString("level") == null) {
						chest.getInventory().setItem(slot, new ItemBuilder(type1, quantity)
								.build());
						return;
					}

					Enchantment enchantment1 = Enchantment.getByName(enchantment.toUpperCase());

					chest.getInventory().setItem(slot, new ItemBuilder(type1, quantity)
							.addEnchant(enchantment1, level)
							.build());
				});
	}

	private void setLocationChestY() {
		double y = loc.getWorld().getHighestBlockYAt(loc);
		this.loc.setY(y);
		this.loc.getBlock().setType(Material.CHEST);
		this.chest = (Chest) loc.getBlock().getState();
	}

	private void locationChest(String world, int x, int z) {
		this.worldName = core.getConfiguration().getConfigurationSection("CenterWorld");
		this.worldNameMax = core.getConfiguration().getConfigurationSection("MaxCoordinates");
		int xRand = new Random().nextInt( worldNameMax.getInt("xMax"));
		int zRand = new Random().nextInt(worldNameMax.getInt("zMax"));
		this.loc = new Location(Bukkit.getWorld(world), xRand + x, 0, zRand + z);
		setLocationChestY();
	}

	public double getLocationChestX() {
		double x = chest.getLocation().getX();
		return x;
	}
	public double getLocationChestY() {
		double y = loc.getWorld().getHighestBlockYAt(loc);
		this.loc.setY(y);
		return y;
	}
	public double getLocationChestZ() {
		double z = chest.getLocation().getZ();
		return z;
	}
}