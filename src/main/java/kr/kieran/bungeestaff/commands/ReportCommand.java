package kr.kieran.bungeestaff.commands;

import kr.kieran.bungeestaff.BungeeStaff;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.text.DecimalFormat;

public class ReportCommand extends Command {

    private BungeeStaff plugin = BungeeStaff.getInstance();

    public ReportCommand() {
        super("report", "bungeestaff.commands.report", "hackusate");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(plugin.getDataUtils().reportCommandUsage);
            return;
        }

        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(plugin.getDataUtils().notAPlayer);
            return;
        }

        ProxiedPlayer executor = (ProxiedPlayer) sender;

        if (plugin.getCooldownUtils().hasReportCooldown(executor)) {
            executor.sendMessage(new TextComponent(plugin.getDataUtils().reportOnCooldown.replace("%time%", String.valueOf(new DecimalFormat("0.0").format(plugin.getCooldownUtils().getReportMillisecondsLeft(executor) / 1000.0)))));
            return;
        }

        StringBuilder builder = new StringBuilder();

        ProxiedPlayer reported = plugin.getProxy().getPlayer(args[0]);
        if (reported == null) {
            sender.sendMessage(new TextComponent(plugin.getDataUtils().invalidPlayer.replace("%player%", args[0])));
            return;
        }

        for (int i = 1; i < args.length; i++) {
            builder.append(args[i]).append(" ");
        }
        String message = builder.toString().trim();
        plugin.getProxy().getPlayers().stream().filter(player -> player.hasPermission("bungeestaff.misc.staff") && !plugin.getPlayerManager().hasMessageToggle(player)).forEach(player -> {
            for (String configMessage : plugin.getConfigUtils().getMessages().getStringList("report-message")) {
                player.sendMessage(new TextComponent(plugin.getDataUtils().c(configMessage.replace("%reporter-server%", executor.getServer().getInfo().getName()).replace("%reported-server%", reported.getServer().getInfo().getName()).replace("%reporter-name%", reported.getName()).replace("%reported-name%", reported.getName()).replace("%message%", message))));
            }
        });
        plugin.getCooldownUtils().putReportCooldown(executor, plugin.getCooldownUtils().getCooldownTime());
    }

}
