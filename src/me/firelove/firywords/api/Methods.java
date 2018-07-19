package me.firelove.firywords.api;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class Methods {

    public static String PREFIX = "§8[§c§lFiryWords§8] §7";
    public static void conMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(Methods.PREFIX+message);
    }
    public static void playSound(Player p, Sound sound) {
        p.playSound(p.getLocation(), sound, 1.0F, 1.0F);
    }
    public static boolean containsIgnoreCase(List<String> list, String soughtFor) {
        for (String current : list) {
            if (current.equalsIgnoreCase(soughtFor)) {
                return true;
            }
        }
        return false;
    }
    public static boolean containsIgnoreCase(String str, String searchStr)     {
        if(str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }
}
