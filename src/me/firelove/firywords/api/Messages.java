package me.firelove.firywords.api;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Messages {

    public static void createFile() {
        File file = new File("plugins/FiryWords/", "messages.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set("Messages.PREFIX", "&8[&cFiryWords&8] &7");
        config.set("Messages.NOPERM", "&cInsufficient Permissions!");
        config.set("Messages.USAGE_ADMIN", "&cUsage: &7/firywords <list/add/remove> <word>");
        config.set("Messages.LIST", "&cCurrently Blocked Words: &8[&7%amount%&8]");
        config.set("Messages.WORD_NOT_BLOCKED", "&7The word &c%word% &7is not blocked!");
        config.set("Messages.ALREADY_BLOCKED", "&7The word &c%word% &7is already blocked!");
        config.set("Messages.WORD_BLOCKED", "&7The word &c%word% &7has been successfully blocked!");
        config.set("Messages.WORD_UNBLOCKED", "&7The word &c%word% &7has been successfully unblocked!");
        config.set("Messages.NOTIFY_1", "&7The player &c%player% &7has used a blocked word!");
        config.set("Messages.NOTIFY_2", "&cMessage: &7%message%");
        config.set("Messages.WARN", "&cPlease stop using rude words!");

        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMessage(String type) {
        File file = new File("plugins/FiryWords/", "messages.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        return config.getString("Messages."+type).replaceAll("&", "§")
                .replaceAll("%amount%", String.valueOf(Words.getBlockedWords().size()));
    }

}
