package kr.kieran.bungeestaff.listeners;

import kr.kieran.bungeestaff.BungeeStaff;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class TabCompleteListeners implements Listener {

    private BungeeStaff plugin;

    public TabCompleteListeners(BungeeStaff plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent event) {
        if (!event.isCancelled()) {
            String partialName = event.getCursor().toLowerCase().lastIndexOf(32) >= 0 ? event.getCursor().toLowerCase().substring(event.getCursor().toLowerCase().lastIndexOf(32) + 1) : event.getCursor().toLowerCase();
            plugin.getProxy().getPlayers().forEach(player -> {
                if (player.getName().toLowerCase().startsWith(partialName) && !event.getSuggestions().contains(player.getName())) {
                    event.getSuggestions().add(player.getName());
                }
            });
        }
    }

}
