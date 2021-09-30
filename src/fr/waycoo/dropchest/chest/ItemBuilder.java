package fr.waycoo.dropchest.chest;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
	private ItemStack is;

	public ItemBuilder(Material m, int amount) {
		is = new ItemStack(m, amount);
	}

	public ItemBuilder(ItemStack is) {
		this.is = is;
	}

	public ItemBuilder(Material m) {
		is = new ItemStack(m);
	}

	public ItemBuilder(Material m, int amount, short damage) {
		is = new ItemStack(m, amount, damage);
	}

	public ItemBuilder setName(String name) {
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		is.setItemMeta(im);
		return this;
	}

	public ItemBuilder setLore(List<String> lore) {
		ItemMeta im = is.getItemMeta();
		im.setLore(lore);
		is.setItemMeta(im);
		return this;
	}

	public ItemBuilder addEnchant(Enchantment enchant, int power) {
		ItemMeta im = is.getItemMeta();
		im.addEnchant(enchant, power, true);
		is.setItemMeta(im);
		return this;
	}

	public ItemBuilder removeEnchant(Enchantment enchant) {
		ItemMeta im = is.getItemMeta();
		im.removeEnchant(enchant);
		return this;
	}

	public ItemBuilder setDurability(short amount) {
		is.setDurability(amount);
		return this;
	}

	public ItemBuilder addHideAttibute(ItemFlag hideAttribute) {
		ItemMeta im = is.getItemMeta();
		im.addItemFlags(hideAttribute);
		return this;
	}

	public ItemBuilder removeHideAttribute(ItemFlag hideAttribute) {
		ItemMeta im = is.getItemMeta();
		im.removeItemFlags(hideAttribute);
		return this;
	}

	public ItemStack build() {
		return this.is;
	}

}