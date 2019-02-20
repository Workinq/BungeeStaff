package kr.kieran.bungeestaff.managers;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.*;

public class PlayerManager {

    private Set<UUID> staffChat;
    private Set<UUID> messageToggle;
    private List<String> onlineStaff;

    public PlayerManager() {
        staffChat = new HashSet<>();
        messageToggle = new HashSet<>();
        onlineStaff = new ArrayList<>();
    }

    public void enableStaffChat(ProxiedPlayer player) {
        staffChat.add(player.getUniqueId());
    }

    public void disableStaffChat(ProxiedPlayer player) {
        staffChat.remove(player.getUniqueId());
    }

    public boolean hasStaffChat(ProxiedPlayer player) {
        return staffChat.contains(player.getUniqueId());
    }

    public void addOnlineStaff(ProxiedPlayer player) {
        onlineStaff.add(player.getName());
    }

    public void removeOnlineStaff(ProxiedPlayer player) {
        onlineStaff.remove(player.getName());
    }

    public boolean isOnlineStaff(ProxiedPlayer player) {
        return onlineStaff.contains(player.getName());
    }

    public void enableMessageToggle(ProxiedPlayer player) {
        messageToggle.add(player.getUniqueId());
    }

    public void disableMessageToggle(ProxiedPlayer player) {
        messageToggle.remove(player.getUniqueId());
    }

    public boolean hasMessageToggle(ProxiedPlayer player) {
        return messageToggle.contains(player.getUniqueId());
    }

    public String getOnlineStaff() {
        if (onlineStaff.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String player : onlineStaff) {
            builder.append(player).append(", ");
        }
        builder.setLength(builder.length() - 2);
        return builder.toString();
    }

    public void term() {
        staffChat.clear();
        messageToggle.clear();
        onlineStaff.clear();
    }

}
