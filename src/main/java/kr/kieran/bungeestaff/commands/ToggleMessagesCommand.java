package kr.kieran.bungeestaff.commands;

import kr.kieran.bungeestaff.BungeeStaff;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ToggleMessagesCommand extends Command {

    private BungeeStaff plugin = BungeeStaff.getInstance();

    public ToggleMessagesCommand() {
        super("togglemessages", "bungeestaff.commands.togglemessages", "tm");
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
                if (plugin.getPlayerManager().hasMessageToggle(player)) {
                    player.sendMessage(plugin.getDataUtils().messageToggleAlreadyEnabled);
                    return;
                }
                toggleMessages(true, player);
            } else if (args[0].equalsIgnoreCase("off")) {
                if (!plugin.getPlayerManager().hasMessageToggle(player)) {
                    player.sendMessage(plugin.getDataUtils().messageToggleAlreadyDisabled);
                    return;
                }
                toggleMessages(false, player);
            }
            return;
        }
        if (plugin.getPlayerManager().hasMessageToggle(player)) {
            toggleMessages(false, player);
        } else {
            toggleMessages(true, player);
        }
    }

    private void toggleMessages(boolean enable, ProxiedPlayer player) {
        if (enable) {
            plugin.getPlayerManager().enableMessageToggle(player);
            player.sendMessage(plugin.getDataUtils().messageToggleEnabled);
        } else {
            plugin.getPlayerManager().disableMessageToggle(player);
            player.sendMessage(plugin.getDataUtils().messageToggleDisabled);
        }
        plugin.getConfigUtils().getSettings().set("toggle-messages." + player.getUniqueId().toString() + ".enabled", enable);
        plugin.getConfigUtils().saveFiles();
    }

}
