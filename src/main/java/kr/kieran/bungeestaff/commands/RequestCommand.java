package kr.kieran.bungeestaff.commands;

import kr.kieran.bungeestaff.BungeeStaff;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.text.DecimalFormat;

public class RequestCommand extends Command {

    private BungeeStaff plugin = BungeeStaff.getInstance();

    public RequestCommand() {
        super("request", "bungeestaff.commands.request", "helpop");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(plugin.getDataUtils().requestCommandUsage);
            return;
        }

        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(plugin.getDataUtils().notAPlayer);
            return;
        }

        ProxiedPlayer executor = (ProxiedPlayer) sender;

        if (plugin.getCooldownUtils().hasRequestCooldown(executor)) {
            executor.sendMessage(new TextComponent(plugin.getDataUtils().requestOnCooldown.replace("%time%", String.valueOf(new DecimalFormat("0.0").format(plugin.getCooldownUtils().getRequestMillisecondsLeft(executor) / 1000.0)))));
            return;
        }

        StringBuilder builder = new StringBuilder();

        for (String arg : args) {
            builder.append(arg).append(" ");
        }
        String message = builder.toString().trim();
        plugin.getProxy().getPlayers().stream().filter(player -> player.hasPermission("bungeestaff.misc.staff") && !plugin.getPlayerManager().hasMessageToggle(player)).forEach(player -> player.sendMessage(new TextComponent(plugin.getDataUtils().requestMessage.replace("%server%", executor.getServer().getInfo().getName()).replace("%player%", executor.getName()).replace("%message%", message))));
        plugin.getCooldownUtils().putRequestCooldown(executor, plugin.getCooldownUtils().getCooldownTime());
    }

}
