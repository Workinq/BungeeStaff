package kr.kieran.bungeestaff;

import kr.kieran.bungeestaff.commands.*;
import kr.kieran.bungeestaff.listeners.PlayerListeners;
import kr.kieran.bungeestaff.listeners.TabCompleteListeners;
import kr.kieran.bungeestaff.managers.PlayerManager;
import kr.kieran.bungeestaff.utils.ConfigurationUtils;
import kr.kieran.bungeestaff.utils.CooldownUtils;
import kr.kieran.bungeestaff.utils.DataUtils;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeStaff extends Plugin {

    private static BungeeStaff instance;
    private ConfigurationUtils configUtils;
    private DataUtils dataUtils;
    private PlayerManager playerManager;
    private CooldownUtils cooldownUtils;

    @Override
    public void onEnable() {
        instance = this;
        registerUtils();
        registerManagers();
        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        instance = null;
        playerManager.term();
        cooldownUtils.term();
        getProxy().getPluginManager().unregisterCommands(this);
        getProxy().getPluginManager().unregisterListeners(this);
    }

    private void registerListeners() {
        getProxy().getPluginManager().registerListener(this, new PlayerListeners(this));
        getProxy().getPluginManager().registerListener(this, new TabCompleteListeners(this));
    }

    private void registerCommands() {
        getProxy().getPluginManager().registerCommand(this, new CoreCommand());
        getProxy().getPluginManager().registerCommand(this, new HubCommand());
        getProxy().getPluginManager().registerCommand(this, new FactionsCommand());
        getProxy().getPluginManager().registerCommand(this, new BroadcastCommand());
        getProxy().getPluginManager().registerCommand(this, new ReportCommand());
        getProxy().getPluginManager().registerCommand(this, new RequestCommand());
        getProxy().getPluginManager().registerCommand(this, new StaffChatCommand());
        getProxy().getPluginManager().registerCommand(this, new ToggleMessagesCommand());
        getProxy().getPluginManager().registerCommand(this, new OnlineStaffCommand());
        getProxy().getPluginManager().registerCommand(this, new StaffVanishCommand());
    }

    private void registerManagers() {
        playerManager = new PlayerManager();
    }

    private void registerUtils() {
        configUtils = new ConfigurationUtils(this);
        dataUtils = new DataUtils();
        cooldownUtils = new CooldownUtils(this);
    }

    public ConfigurationUtils getConfigUtils() {
        return configUtils;
    }

    public DataUtils getDataUtils() {
        return dataUtils;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public CooldownUtils getCooldownUtils() {
        return cooldownUtils;
    }

    public static BungeeStaff getInstance() {
        return instance;
    }

}
