package kr.kieran.bungeestaff.commands;

import kr.kieran.bungeestaff.BungeeStaff;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class StaffChatCommand extends Command {

    private BungeeStaff plugin = BungeeStaff.getInstance();

    public StaffChatCommand() {
        super("staffchat", "bungeestaff.commands.staffchat", "sc");
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

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("on")) {
                if (plugin.getPlayerManager().hasStaffChat(player)) {
                    player.sendMessage(plugin.getDataUtils().staffChatAlreadyEnabled);
                    return;
                }
                manageChat(true, player);
            } else if (args[0].equalsIgnoreCase("off")) {
                if (!plugin.getPlayerManager().hasStaffChat(player)) {
                    player.sendMessage(plugin.getDataUtils().staffChatAlreadyDisabled);
                    return;
                }
                manageChat(false, player);
            }
            return;
        }
        if (plugin.getPlayerManager().hasStaffChat(player)) {
            manageChat(false, player);
        } else {
            manageChat(true, player);
        }
    }

    private void manageChat(boolean enable, ProxiedPlayer player) {
        if (enable) {
            plugin.getPlayerManager().enableStaffChat(player);
            player.sendMessage(plugin.getDataUtils().staffChatEnabled);
        } else {
            plugin.getPlayerManager().disableStaffChat(player);
            player.sendMessage(plugin.getDataUtils().staffChatDisabled);
        }
        plugin.getConfigUtils().getSettings().set("staff-chat." + player.getUniqueId().toString() + ".enabled", enable);
        plugin.getConfigUtils().saveFiles();
    }

}
