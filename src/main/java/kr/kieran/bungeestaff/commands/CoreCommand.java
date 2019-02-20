package kr.kieran.bungeestaff.commands;

import kr.kieran.bungeestaff.BungeeStaff;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class CoreCommand extends Command {

    private BungeeStaff plugin = BungeeStaff.getInstance();

    public CoreCommand() {
        super("bungeestaff", "bungeestaff.commands.bungeestaff", "bs");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(plugin.getDataUtils().coreCommandUsage);
            return;
        }
        switch (args[0]) {
            case "help":
                sender.sendMessage(new TextComponent(plugin.getDataUtils().c("&c&l&m----------&r&7 BungeeStaff Help &c&l&m----------")));
                sender.sendMessage(new TextComponent(plugin.getDataUtils().c("&c/report <player> <reason> &7- Reports a player to staff online.")));
                sender.sendMessage(new TextComponent(plugin.getDataUtils().c("&c/request <message> &7- Sends a message to all online staff if you need help.")));
                sender.sendMessage(new TextComponent(plugin.getDataUtils().c("&c/staff &7- Displays a message showing all online staff.")));
                if (sender.hasPermission("bungeestaff.misc.staff")) {
                    sender.sendMessage(new TextComponent(plugin.getDataUtils().c("&c/bungeestaff help &7- Displays the plugin help message.")));
                    sender.sendMessage(new TextComponent(plugin.getDataUtils().c("&c/bungeestaff reload &7- Reloads the plugin's configuration files.")));
                    //sender.sendMessage(new TextComponent(plugin.getDataUtils().c("&c/maintenance [on | off] &7- Enables/disables the server's maintenance mode.")));
                    sender.sendMessage(new TextComponent(plugin.getDataUtils().c("&c/broadcast <message> &7- Broadcasts a message to the entire network.")));
                    sender.sendMessage(new TextComponent(plugin.getDataUtils().c("&c/staffchat [on | off] &7- Enables/disables your staff chat.")));
                }
                return;
            case "reload":
                if (!sender.hasPermission("bungeestaff.commands.reload")) {
                    sender.sendMessage(plugin.getDataUtils().noPermission);
                    return;
                }
                plugin.getConfigUtils().reloadFiles();
                sender.sendMessage(plugin.getDataUtils().configurationReloaded);
                return;
            default:
                sender.sendMessage(plugin.getDataUtils().coreCommandUsage);
        }
    }

}
