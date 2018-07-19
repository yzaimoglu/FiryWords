package me.firelove.firywords.api;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {

    public static void createFile() {
        File file = new File("plugins/FiryWords/", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set("Permissions.admin", "firywords.admin");
        config.set("Permissions.notify", "firywords.notify");
        config.set("Permissions.ignore", "firywords.ignore");
        config.set("Permissions.list", "firywords.list");

        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPermission(String permission) {
        File file = new File("plugins/FiryWords/", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        return config.getString("Permissions."+permission);
    }
}
