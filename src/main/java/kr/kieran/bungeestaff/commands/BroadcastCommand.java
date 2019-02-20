package kr.kieran.bungeestaff.commands;

import kr.kieran.bungeestaff.BungeeStaff;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class BroadcastCommand extends Command {

    private BungeeStaff plugin = BungeeStaff.getInstance();

    public BroadcastCommand() {
        super("broadcast", "bungeestaff.commands.broadcast", "bc", "announce");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage(plugin.getDataUtils().noPermission);
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(arg).append(" ");
        }
        String broadcast = builder.toString().trim();
        plugin.getProxy().broadcast(new TextComponent(plugin.getDataUtils().broadcastMessage.replace("%message%", broadcast)));
    }

}
