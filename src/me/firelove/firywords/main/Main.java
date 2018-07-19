package me.firelove.firywords.main;

import me.firelove.firywords.api.Config;
import me.firelove.firywords.api.Messages;
import me.firelove.firywords.api.Methods;
import me.firelove.firywords.api.Words;
import me.firelove.firywords.commands.FiryWordsCMD;
import me.firelove.firywords.listeners.ChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    public static String NAME = "FiryWords";
    public static double VERSION = 1.0;
    public static String AUTHOR = "Firelove";

    @Override
    public void onEnable() {

        try {
            initialize();
        } catch (Exception e) {
            Methods.conMessage("§cAn error occured, the plugin can't be initialized. The server is shutting down!");
            Bukkit.shutdown();
        }

    }

    @Override
    public void onDisable() {
        Methods.conMessage("§cThanks for using "+NAME+" "+VERSION+"!");
        Methods.conMessage("§cDeveloper: Firelove");
    }

    private void initialize() {
        File messages = new File("plugins/FiryWords/", "messages.yml");
        File config = new File("plugins/FiryWords/", "config.yml");
        File words = new File("plugins/FiryWords/", "words.yml");

        if(!messages.exists()) {
            Messages.createFile();
        }
        if(!config.exists()) {
            Config.createFile();
        }
        if(!words.exists()) {
            Words.createFile();
        }

        initCommands();
        initListeners();
        Methods.conMessage("§c"+NAME+" "+VERSION+" has been successfully initialized!");
        Methods.conMessage("§cDeveloper: Firelove");
    }

    private void initCommands() {

        getCommand("firywords").setExecutor(new FiryWordsCMD());

    }

    private void initListeners() {
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
    }
}
