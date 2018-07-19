package me.firelove.firywords.listeners;

import me.firelove.firywords.api.Config;
import me.firelove.firywords.api.Messages;
import me.firelove.firywords.api.Methods;
import me.firelove.firywords.api.Words;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class ChatEvent implements Listener {

    @EventHandler
    public void on(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        List<String> blocked_words = Words.getBlockedWords();

        for(String blocked : blocked_words) {
            if(Methods.containsIgnoreCase(e.getMessage(), blocked)) {
                if(p.hasPermission(Config.getPermission("ignore"))) {
                    e.setCancelled(false);
                    return;
                }
                e.setCancelled(true);
                for(Player all : Bukkit.getOnlinePlayers()) {
                    if(all.hasPermission(Config.getPermission("notify")) || all.hasPermission(Config.getPermission("admin"))) {
                        p.sendMessage(Messages.getMessage("PREFIX")+
                            Messages.getMessage("NOTIFY_1").replaceAll("%player%", p.getName()));
                        Bukkit.getConsoleSender().sendMessage(Messages.getMessage("PREFIX")+
                                Messages.getMessage("NOTIFY_1").replaceAll("%player%", p.getName()));
                        p.sendMessage(Messages.getMessage("PREFIX")+
                            Messages.getMessage("NOTIFY_2").replaceAll("%message%", e.getMessage()));
                        Bukkit.getConsoleSender().sendMessage(Messages.getMessage("PREFIX")+
                                Messages.getMessage("NOTIFY_2").replaceAll("%message%", e.getMessage()));
                        Methods.playSound(all, Sound.BLOCK_WOOD_BUTTON_CLICK_ON);
                    }
                }
                p.sendMessage(Messages.getMessage("PREFIX")+
                    Messages.getMessage("WARN"));
                return;
            }
        }

    }

}
