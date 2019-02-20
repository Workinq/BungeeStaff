package kr.kieran.bungeestaff.utils;

import kr.kieran.bungeestaff.BungeeStaff;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownUtils {

    private int cooldownTime;
    private Map<UUID, Long> reportCooldown;
    private Map<UUID, Long> requestCooldown;

    public CooldownUtils(BungeeStaff plugin) {
        cooldownTime = plugin.getConfigUtils().getConfig().getInt("cooldown-time");
        reportCooldown = new HashMap<>();
        requestCooldown = new HashMap<>();
    }

    public void putReportCooldown(ProxiedPlayer player, long time) {
        long value = System.currentTimeMillis() + time * 1000L;
        reportCooldown.put(player.getUniqueId(), value);
    }

    public void removeReportCooldown(ProxiedPlayer player) {
        reportCooldown.remove(player.getUniqueId());
    }

    public boolean hasReportCooldown(ProxiedPlayer player) {
        if (!reportCooldown.containsKey(player.getUniqueId())) {
            return false;
        }
        long value = reportCooldown.get(player.getUniqueId());
        return  value > System.currentTimeMillis();
    }

    public long getReportMillisecondsLeft(ProxiedPlayer player) {
        if (!hasReportCooldown(player)) {
            return -1L;
        }
        return reportCooldown.get(player.getUniqueId()) - System.currentTimeMillis();
    }

    public void putRequestCooldown(ProxiedPlayer player, long time) {
        long value = System.currentTimeMillis() + time * 1000L;
        requestCooldown.put(player.getUniqueId(), value);
    }

    public void removeRequestCooldown(ProxiedPlayer player) {
        requestCooldown.remove(player.getUniqueId());
    }

    public boolean hasRequestCooldown(ProxiedPlayer player) {
        if (!requestCooldown.containsKey(player.getUniqueId())) {
            return false;
        }
        long value = requestCooldown.get(player.getUniqueId());
        return  value > System.currentTimeMillis();
    }

    public long getRequestMillisecondsLeft(ProxiedPlayer player) {
        if (!hasRequestCooldown(player)) {
            return -1L;
        }
        return requestCooldown.get(player.getUniqueId()) - System.currentTimeMillis();
    }

    public int getCooldownTime() {
        return cooldownTime;
    }

    public void term() {
        reportCooldown.clear();
        requestCooldown.clear();
    }

}
