package kr.kieran.bungeestaff.commands;

import kr.kieran.bungeestaff.BungeeStaff;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HubCommand extends Command {

    private BungeeStaff plugin = BungeeStaff.getInstance();

    public HubCommand() {
        super("hub", "bungeestaff.commands.hub", "lobby");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(plugin.getDataUtils().notAPlayer);
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (!player.hasPermission(getPermission())) {
            player.sendMessage(plugin.getDataUtils().noPermission);
            return;
        }

        if (player.getServer().getInfo().getName().equalsIgnoreCase("lobby")) {
            player.sendMessage(plugin.getDataUtils().alreadyInHub);
            return;
        }
        player.sendMessage(plugin.getDataUtils().connectingToHub);
        player.connect(ProxyServer.getInstance().getServerInfo("lobby"));
    }

}
