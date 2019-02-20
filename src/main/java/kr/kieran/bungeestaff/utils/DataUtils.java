package kr.kieran.bungeestaff.utils;

import kr.kieran.bungeestaff.BungeeStaff;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class DataUtils {

    private BungeeStaff plugin = BungeeStaff.getInstance();

    public String c(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public TextComponent notAPlayer = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("not-a-player")));
    public TextComponent noPermission = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("no-permission")));
    public TextComponent alreadyInHub = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("already-in-hub")));
    public TextComponent alreadyInFactions = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("already-in-factions")));
    public TextComponent connectingToHub = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("connecting-to-hub")));
    public TextComponent connectingToFactions = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("connecting-to-factions")));
    public TextComponent coreCommandUsage = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("core-command-usage")));
    public TextComponent reportCommandUsage = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("report-command-usage")));
    public TextComponent requestCommandUsage = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("request-command-usage")));
    public TextComponent configurationReloaded = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("configuration-reloaded")));
    public TextComponent staffChatEnabled = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("staff-chat-enabled")));
    public TextComponent staffChatAlreadyEnabled = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("staff-chat-already-enabled")));
    public TextComponent staffChatDisabled = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("staff-chat-disabled")));
    public TextComponent staffChatAlreadyDisabled = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("staff-chat-already-disabled")));
    public TextComponent messageToggleEnabled = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("message-toggle-enabled")));
    public TextComponent messageToggleAlreadyEnabled = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("message-toggle-already-enabled")));
    public TextComponent messageToggleDisabled = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("message-toggle-disabled")));
    public TextComponent messageToggleAlreadyDisabled = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("message-toggle-already-disabled")));
    public TextComponent staffVanishEnabled = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("staff-vanish-enabled")));
    public TextComponent staffVanishAlreadyEnabled = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("staff-vanish-already-enabled")));
    public TextComponent staffVanishDisabled = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("staff-vanish-disabled")));
    public TextComponent staffVanishAlreadyDisabled = new TextComponent(c(plugin.getConfigUtils().getMessages().getString("staff-vanish-already-disabled")));
    public String staffMessage = (c(plugin.getConfigUtils().getMessages().getString("staff-message")));
    public String requestMessage = (c(plugin.getConfigUtils().getMessages().getString("request-message")));
    public String invalidPlayer = c(plugin.getConfigUtils().getMessages().getString("invalid-player"));
    public String onlineStaffMessage = c(plugin.getConfigUtils().getMessages().getString("online-staff-message"));
    public String reportOnCooldown = c(plugin.getConfigUtils().getMessages().getString("report-on-cooldown"));
    public String requestOnCooldown = c(plugin.getConfigUtils().getMessages().getString("request-on-cooldown"));
    public String broadcastMessage = c(plugin.getConfigUtils().getMessages().getString("broadcast-message"));

}
