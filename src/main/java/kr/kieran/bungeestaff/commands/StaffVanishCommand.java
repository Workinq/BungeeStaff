package kr.kieran.bungeestaff.commands;

import kr.kieran.bungeestaff.BungeeStaff;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class StaffVanishCommand extends Command {

    private BungeeStaff plugin = BungeeStaff.getInstance();

    public StaffVanishCommand() {
        super("staffvanish", "bungeestaff.commands.staffvanish", "staffv");
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
                if (!plugin.getPlayerManager().isOnlineStaff(player)) {
                    player.sendMessage(plugin.getDataUtils().staffVanishAlreadyEnabled);
                    return;
                }
                handleVanish(true, player);
            } else if (args[0].equalsIgnoreCase("off")) {
                if (plugin.getPlayerManager().isOnlineStaff(player)) {
                    player.sendMessage(plugin.getDataUtils().staffVanishAlreadyDisabled);
                    return;
                }
                handleVanish(false, player);
            }
            return;
        }
        if (!plugin.getPlayerManager().isOnlineStaff(player)) {
            handleVanish(false, player);
        } else {
            handleVanish(true, player);
        }
    }

    private void handleVanish(boolean enabled, ProxiedPlayer player) {
        if (enabled) {
            plugin.getPlayerManager().removeOnlineStaff(player);
            player.sendMessage(plugin.getDataUtils().staffVanishEnabled);
        } else {
            plugin.getPlayerManager().addOnlineStaff(player);
            player.sendMessage(plugin.getDataUtils().staffVanishDisabled);
        }
        plugin.getConfigUtils().getSettings().set("staff-vanish." + player.getUniqueId().toString() + ".enabled", enabled);
        plugin.getConfigUtils().saveFiles();
    }

}
