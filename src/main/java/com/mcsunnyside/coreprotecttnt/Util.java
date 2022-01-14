package com.mcsunnyside.coreprotecttnt;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Util {
    public static void broadcastNearPlayers(Location location, String message) {
        if (message == null || message.isEmpty()) {
            return; // Do not send empty message
        }
        String msg = ChatColor.translateAlternateColorCodes('&', message);
        for (Player player : location.getWorld().getPlayers()) {
            Vector playerVector = player.getLocation().toVector();
            Vector centralVector = location.toVector();
            if(playerVector.distanceSquared(centralVector) < 15){
                player.sendMessage(msg);
            }
        }
    }

    public static ConfigurationSection bakeConfigSection(Configuration configuration, String path) {
        ConfigurationSection section = configuration.getConfigurationSection(path);
        if (section == null) {
            // Create default section with default values
            section = configuration.createSection(path);
            section.set("enable", true);
            section.set("disable-unknown", true);
            section.set("alert", ChatColor.RED + "Failed to read translation, configuration section missing!");
        }
        return section;
    }
}