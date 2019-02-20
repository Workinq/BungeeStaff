package kr.kieran.bungeestaff.listeners;

import kr.kieran.bungeestaff.BungeeStaff;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerListeners implements Listener {

    private BungeeStaff plugin;

    public PlayerListeners(BungeeStaff plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if (plugin.getConfigUtils().getSettings().getString("staff-chat." + player.getUniqueId().toString() + ".enabled") != null) {
            if (plugin.getConfigUtils().getSettings().getBoolean("staff-chat." + player.getUniqueId().toString() + ".enabled") && player.hasPermission("bungeestaff.misc.staff")) {
                plugin.getPlayerManager().enableStaffChat(player);
            }
        }
        if (plugin.getConfigUtils().getSettings().getString("staff-vanish." + player.getUniqueId().toString() + ".enabled") != null) {
            if (!plugin.getConfigUtils().getSettings().getBoolean("staff-vanish." + player.getUniqueId().toString() + ".enabled") && player.hasPermission("bungeestaff.misc.staff")) {
                plugin.getPlayerManager().addOnlineStaff(player);
            }
        }
        if (plugin.getConfigUtils().getSettings().getString("toggle-messages." + player.getUniqueId().toString() + ".enabled") != null) {
            if (plugin.getConfigUtils().getSettings().getBoolean("toggle-messages." + player.getUniqueId().toString() + ".enabled") && player.hasPermission("bungeestaff.misc.staff")) {
                plugin.getPlayerManager().enableStaffChat(player);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerDisconnectEvent event) {
        handleMemoryLeaks(event.getPlayer());
    }

    @EventHandler
    public void onPlayerKick(ServerKickEvent event) {
        handleMemoryLeaks(event.getPlayer());
    }

    private void handleMemoryLeaks(ProxiedPlayer player) {
        if (plugin.getPlayerManager().isOnlineStaff(player)) {
            plugin.getPlayerManager().removeOnlineStaff(player);
        }
        if (plugin.getPlayerManager().hasStaffChat(player)) {
            plugin.getPlayerManager().disableStaffChat(player);
        }
        if (plugin.getPlayerManager().hasMessageToggle(player)) {
            plugin.getPlayerManager().disableMessageToggle(player);
        }
        if (plugin.getCooldownUtils().hasReportCooldown(player)) {
            plugin.getCooldownUtils().removeReportCooldown(player);
        }
        if (plugin.getCooldownUtils().hasRequestCooldown(player)) {
            plugin.getCooldownUtils().removeRequestCooldown(player);
        }
    }

    @EventHandler
    public void onPlayerChat(ChatEvent event) {
        ProxiedPlayer sender = (ProxiedPlayer) event.getSender();
        if (event.getMessage().startsWith("/")) return;
        if (plugin.getPlayerManager().hasStaffChat(sender)) {
            event.setCancelled(true);
            plugin.getProxy().getPlayers().stream().filter(player -> player.hasPermission("bungeestaff.misc.staff")).forEach(player -> player.sendMessage(new TextComponent(plugin.getDataUtils().staffMessage.replace("%server%", player.getServer().getInfo().getName()).replace("%player%", player.getName()).replace("%message%", event.getMessage()))));
        }
    }

}
