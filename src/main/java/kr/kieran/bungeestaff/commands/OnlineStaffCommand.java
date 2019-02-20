package kr.kieran.bungeestaff.commands;

import kr.kieran.bungeestaff.BungeeStaff;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class OnlineStaffCommand extends Command {

    private BungeeStaff plugin = BungeeStaff.getInstance();

    public OnlineStaffCommand() {
        super("onlinestaff", "bungeestaff.commands.onlinestaff", "staff");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(new TextComponent(plugin.getDataUtils().onlineStaffMessage.replace("%staff%", plugin.getPlayerManager().getOnlineStaff())));
    }

}
