package me.br.raulzindograu.lkup.Lagg;

import me.br.raulzindograu.lkup.Lagg.commands.Commands;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {

    String prefix = "§a§l[☣] ";

    public static Main getInstance() {
        return Main.getPlugin(Main.class);
    }

    public void clearfloorautomatic() {
        new BukkitRunnable() {
            int tempo = 61;

            public void run() {
                this.tempo -= 1;
                if (this.tempo == 60) {
                    Bukkit.broadcastMessage(prefix + "§7§l┃ §f§lOs itens que causam radiação serão removidos §2§n60§f§l segundos!");
                    return;
                }
                if (this.tempo == 30) {
                    Bukkit.broadcastMessage(prefix + "§7§l┃ §f§lOs itens que causam radiação serão removidos §2§n30§f§l segundos!");
                    return;
                }
                if (this.tempo == 10) {
                    Bukkit.broadcastMessage(prefix + "§7§l┃ §f§lOs itens que causam radiação serão removidos §2§n10§f§l segundos!");
                    return;
                }
                if (this.tempo == 4) {
                    Bukkit.broadcastMessage(prefix + "§7§l┃ §f§lOs itens que causam radiação serão removidos §2§n3§f§l ssegundos!");
                    return;
                }
                if (this.tempo == 3) {
                    Bukkit.broadcastMessage(prefix + "§7§l┃ §f§lOs itens que causam radiação serão removidos §2§n2§f§l segundos!");
                    return;
                }
                if (this.tempo == 2) {
                    Bukkit.broadcastMessage(prefix + "§7§l┃ §f§lOs itens que causam radiação serão removidos §2§n1§f§l segundos!");
                    return;
                }
                if (this.tempo == 1) {

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
                            Bukkit.broadcastMessage(prefix + "§7§l┃ §f§lNenhum item que causa radiação foi encontrado no chão...");
                            return;
                        }
                        for (Player on : Bukkit.getOnlinePlayers()) {
                        Bukkit.broadcastMessage(prefix + "§7§l┃ §f§lForam removidas §2§n" + entidades + "§f§l itens do chão que causam radiação.");
                        sendActionBar(on, prefix + "§7§l┃ §f§lForam encontrados §2§n" + entidades + "§f§l Mobs Radiotivos que causam radiação.");
                        return;
                    }
                }
            }
        }.runTaskTimer(getInstance(), 0L, 20L);
        Bukkit.getConsoleSender().sendMessage(prefix + "§7§l┃ §f§lChunck Descarregados.");
    }

    public static void sendActionBar(Player p, String msg) {
        PacketPlayOutChat action = new PacketPlayOutChat(new ChatComponentText(msg), (byte) 2);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(action);
    }

    @Override
    public void onEnable() {
        new BukkitRunnable() {
            public void run () {
                Main.this.clearfloorautomatic();
            }
        }.runTaskTimer(this, 0L, 20L * 600L);
                Bukkit.getConsoleSender().sendMessage("§aPlugin de antilag ativo!");
                getCommand("lag").setExecutor(new Commands());
    }
}
