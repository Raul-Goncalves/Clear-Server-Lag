package me.br.raulzindograu.lkup.Lagg.commands;

import me.br.raulzindograu.lkup.Lagg.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {

        String prefix = "§a§l[☣] ";

        String[] ajuda = {
                "",
                " §2§m---§A§m---§f§l[LKUP ANTI LAG]§A§m---§2§m---",
                "",
                "§4✚ §c§o/lag limpar mobs §7§l┃ §f§lLimpar mobs dos mundos.",
                "§4✚ §c§o/lag limpar itens §7§l┃ §f§lLimpar itens dos mundos.",
                "§4✚ §c§o/lag reload §7§l┃ §f§lPara regarregar as configurações.",
                "§4✚ §c§o/lag ajuda §7§l┃ §f§lObter ajuda.",
                ""
        };

        if ((s instanceof Player)) {
            Player p = (Player) s;
            if (cmd.getName().equalsIgnoreCase("lag")) {
                if (!p.hasPermission("lkup.lag.usar")) {

                    p.sendMessage(prefix + "§cVocê preficsa um cargo superior ao seu, para utilizar esse comando!");
                    return true;
                }
                if (args.length == 0) {
                    p.sendMessage(ajuda);
                    return true;
                }
                if (args.length == 1) {
                    if ((!args[0].equalsIgnoreCase("limpar")) && (!args[0].equalsIgnoreCase("reload")) && (!args[0].equalsIgnoreCase("ajuda"))) {
                        p.sendMessage(prefix + "§cComando  secundário não encontrado, Use §c§o/lag ajuda");
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("limpar")) {
                        p.sendMessage(prefix + "§cComando  secundário não encontrado, Use §c§o/lag ajuda");
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("reload")) {
                        p.sendMessage(prefix + "§7§l┃ §f§lConfigurações recarregadas sem falhas");
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("ajuda")) {
                        p.sendMessage(ajuda);
                        return true;
                    }
                    return true;
                }
                if (args.length >= 2) {
                    if ((!args[0].equalsIgnoreCase("limpar")) && (!args[0].equalsIgnoreCase("reload")) && (!args[0].equalsIgnoreCase("ajuda"))) {
                        p.sendMessage(prefix + "§cComando  secundário não encontrado, Use §c§o/lag ajuda");
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("limpar")) {
                        if ((!args[0].equalsIgnoreCase("itens")) && (!args[0].equalsIgnoreCase("mobs"))) {
                            p.sendMessage(prefix + "§cComando  secundário não encontrado, Use §c§o/lag ajuda");
                        }
                        if (args[1].equalsIgnoreCase("itens")) {
                            int entidades = 0;
                            for (World worlds : Bukkit.getWorlds()) {
                                for (Entity ents : worlds.getEntities()) {
                                    if (ents instanceof Item && !(ents instanceof ItemFrame)) {
                                        entidades++;
                                        ents.remove();
                                    }
                                }
                            }
                            if (entidades == 0) {
                                p.sendMessage(prefix + "§7§l┃ §f§lNenhum item que causa radiação foi encontrado no chão...");
                                Main.sendActionBar(p, prefix + "§7§l┃ §f§lNenhum item que causa radiação foi encontrado no chão...");
                                return true;
                            }
                            if (entidades == 1) {
                                for (Player on : Bukkit.getOnlinePlayers()) {
                                    on.sendMessage(prefix + "§7§l┃ §f§lForam removidas §2§n" + entidades + "§f§l itens do chão que causam radiação.");
                                    Main.sendActionBar(on, prefix + "§7§l┃ §f§lForam removidas §2§n" + entidades + "§f§l itens do chão que causam radiação.");
                                }
                                return true;
                            }
                            for (Player on : Bukkit.getOnlinePlayers()) {
                                on.sendMessage(prefix + "§7§l┃ §f§lForam removidas §2§n" + entidades + "§f§l itens do chão que causam radiação.");
                                Main.sendActionBar(on, prefix + "§7§l┃ §f§lForam removidas §2§n" + entidades + "§f§l itens do chão que causam radiação.");
                            }
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("mobs")) {
                            int entidades = 0;
                            for (World worlds : Bukkit.getWorlds()) {
                                for (Entity ents : worlds.getEntities()) {
                                    if (ents instanceof Creature && !(ents instanceof Witch)) {
                                        entidades++;
                                        ents.remove();
                                    }
                                }
                            }
                            if (entidades == 0) {
                                for (Player on : Bukkit.getOnlinePlayers()) {
                                    p.sendMessage(prefix + "§7§l┃ §f§lNenhum Mob Radiotivos foi encontrado...");
                                    Main.sendActionBar(p, prefix + "§7§l┃ §f§lNenhum Mob Radiotivos foi encontrado...");
                                }
                                return true;
                            }
                            if (entidades == 1) {
                                for (Player on : Bukkit.getOnlinePlayers()) {
                                    on.sendMessage(prefix + "§7§l┃ §f§lForam encontrados §2§n" + entidades + "§f§l Mobs Radiotivos que causam radiação.");
                                    Main.sendActionBar(on, prefix + "§7§l┃ §f§lForam encontrados §2§n" + entidades + "§f§l Mobs Radiotivos que causam radiação.");
                                }
                                return true;
                            }
                            for (Player on : Bukkit.getOnlinePlayers()) {
                                on.sendMessage(prefix + "§7§l┃ §f§lForam encontrados §2§n" + entidades + "§f§l Mobs Radiotivos que causam radiação.");
                                Main.sendActionBar(on, prefix + "§7§l┃ §f§lForam encontrados §2§n" + entidades + "§f§l Mobs Radiotivos que causam radiação.");
                            }
                            return true;
                        }
                        return true;
                    }
                    return true;
                }
            }
        }
        return true;
    }
}
