package me.firelove.firywords.commands;

import me.firelove.firywords.api.Config;
import me.firelove.firywords.api.Messages;
import me.firelove.firywords.api.Methods;
import me.firelove.firywords.api.Words;
import me.firelove.firywords.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class FiryWordsCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission(Config.getPermission("admin"))) {


                if(args.length == 1) {
                    String arg0 = args[0];
                    if(arg0.equalsIgnoreCase("list")) {
                        p.sendMessage(Messages.getMessage("PREFIX")+
                                Messages.getMessage("LIST"));
                        for(String all : Words.getBlockedWords()) {
                            p.sendMessage(" §7- §c"+all);
                        }
                    } else if(arg0.equalsIgnoreCase("version")) {
                        p.sendMessage(Messages.getMessage("PREFIX")+
                                "&cRunning "+ Main.NAME+" "+Main.VERSION+"!");
                        p.sendMessage("&cDeveloper: "+Main.AUTHOR);
                    } else {
                        p.sendMessage(Messages.getMessage("PREFIX")+
                                Messages.getMessage("USAGE_ADMIN"));
                    }
                } else if(args.length == 2) {
                    String arg0 = args[0];
                    String arg1 = args[1];
                    if(arg0.equalsIgnoreCase("add")) {
                        if(Methods.containsIgnoreCase(Words.getBlockedWords(), arg1)) {
                            p.sendMessage(Messages.getMessage("PREFIX")+
                                    Messages.getMessage("ALREADY_BLOCKED").replaceAll("%word%", arg1));
                        } else {
                            Words.addBlockedWord(arg1);
                            p.sendMessage(Messages.getMessage("PREFIX")+
                                    Messages.getMessage("WORD_BLOCKED").replaceAll("%word%", arg1));
                        }
                    } else if(arg0.equalsIgnoreCase("remove")) {
                        if(Methods.containsIgnoreCase(Words.getBlockedWords(), arg1)) {
                            boolean bool = false;
                            int i = -1;
                            while(!bool) {
                                i++;
                                if(arg1.equalsIgnoreCase(Words.getBlockedWords().get(i))) {
                                    bool = true;
                                } else {
                                    bool = false;
                                }
                            }
                            Words.removeBlockedWord(Words.getBlockedWords().get(i));
                            p.sendMessage(Messages.getMessage("PREFIX")+
                                    Messages.getMessage("WORD_UNBLOCKED").replaceAll("%word%", arg1));
                        } else {
                            p.sendMessage(Messages.getMessage("PREFIX")+
                                    Messages.getMessage("WORD_NOT_BLOCKED").replaceAll("%word%", arg1));
                        }
                    }
                } else {
                    p.sendMessage(Messages.getMessage("PREFIX")+
                            Messages.getMessage("USAGE_ADMIN"));
                }

            } else if(p.hasPermission(Config.getPermission("list"))) {
                p.sendMessage(Messages.getMessage("PREFIX")+
                        Messages.getMessage("LIST"));
                for(String all : Words.getBlockedWords()) {
                    p.sendMessage(" §7- §c"+all);
                }
            } else {
                p.sendMessage(Messages.getMessage("PREFIX")+
                        Messages.getMessage("NOPERM"));
                Methods.playSound(p, Sound.BLOCK_ANVIL_BREAK);
                return false;
            }
            Methods.playSound(p, Sound.ENTITY_PLAYER_LEVELUP);

        } else {
            ConsoleCommandSender p = Bukkit.getConsoleSender();

            if(args.length == 1) {
                String arg0 = args[0];
                if(arg0.equalsIgnoreCase("list")) {
                    p.sendMessage(Messages.getMessage("PREFIX")+
                            Messages.getMessage("LIST"));
                    for(String all : Words.getBlockedWords()) {
                        p.sendMessage(" §7- §c"+all);
                    }
                } else {
                    p.sendMessage(Messages.getMessage("PREFIX")+
                            Messages.getMessage("USAGE_ADMIN"));
                }
            } else if(args.length == 2) {
                String arg0 = args[0];
                String arg1 = args[1];
                if(arg0.equalsIgnoreCase("add")) {
                    if(Methods.containsIgnoreCase(Words.getBlockedWords(), arg1)) {
                        p.sendMessage(Messages.getMessage("PREFIX")+
                        Messages.getMessage("ALREADY_BLOCKED").replaceAll("%word%", arg1));
                    } else {
                        Words.addBlockedWord(arg1);
                        p.sendMessage(Messages.getMessage("PREFIX")+
                        Messages.getMessage("WORD_BLOCKED").replaceAll("%word%", arg1));
                    }
                } else if(arg0.equalsIgnoreCase("remove")) {
                    if(Methods.containsIgnoreCase(Words.getBlockedWords(), arg1)) {
                        boolean bool = false;
                        int i = -1;
                        while(!bool) {
                            i++;
                            if(arg1.equalsIgnoreCase(Words.getBlockedWords().get(i))) {
                                bool = true;
                            } else {
                                bool = false;
                            }
                        }
                        Words.removeBlockedWord(Words.getBlockedWords().get(i));
                        p.sendMessage(Messages.getMessage("PREFIX")+
                        Messages.getMessage("WORD_UNBLOCKED").replaceAll("%word%", arg1));
                    } else {
                        p.sendMessage(Messages.getMessage("PREFIX")+
                                Messages.getMessage("WORD_NOT_BLOCKED").replaceAll("%word%", arg1));
                    }
                }
            } else {
                p.sendMessage(Messages.getMessage("PREFIX")+
                        Messages.getMessage("USAGE_ADMIN"));
            }

        }



        return false;
    }
}
