package me.firelove.firywords.api;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Words {

    private static List<String> blocked_words = new ArrayList<>();

    public static void createFile() {
        blocked_words.add("Motherfucker");
        blocked_words.add("Fuck");
        blocked_words.add("Bitch");

        File file = new File("plugins/FiryWords/", "words.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set("Blocked Words", blocked_words);

        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getBlockedWords() {
        File file = new File("plugins/FiryWords/", "words.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        return config.getStringList("Blocked Words");
    }

    public static void addBlockedWord(String word) {
        File file = new File("plugins/FiryWords/", "words.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        blocked_words = Words.getBlockedWords();
        blocked_words.add(word);
        config.set("Blocked Words", blocked_words);

        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeBlockedWord(String word) {
        File file = new File("plugins/FiryWords/", "words.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        blocked_words = Words.getBlockedWords();
        blocked_words.remove(word);
        config.set("Blocked Words", blocked_words);

        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setBlockedWords(List<String> words) {
        File file = new File("plugins/FiryWords/", "words.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set("Blocked Words", words);

        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}