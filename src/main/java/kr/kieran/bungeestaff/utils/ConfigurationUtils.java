package kr.kieran.bungeestaff.utils;

import kr.kieran.bungeestaff.BungeeStaff;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;

public class ConfigurationUtils {

    private BungeeStaff plugin;
    private Configuration config, messages, settings;

    public ConfigurationUtils(BungeeStaff plugin) {
        this.plugin = plugin;

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        File configFile = new File(plugin.getDataFolder(), "config.yml");
        File messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        File settingsFile = new File(plugin.getDataFolder(), "settings.yml");

        try {
            if (!configFile.exists()) {
                InputStream in = plugin.getResourceAsStream("config.yml");
                Files.copy(in, configFile.toPath());
            }
            if (!messagesFile.exists()) {
                InputStream in = plugin.getResourceAsStream("messages.yml");
                Files.copy(in, messagesFile.toPath());
            }
            if (!settingsFile.exists()) {
                InputStream in = plugin.getResourceAsStream("settings.yml");
                Files.copy(in, settingsFile.toPath());
            }
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to create plugin files.");
        }
        registerConfigurations();
    }

    private void registerConfigurations() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), "config.yml"));
            messages = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), "messages.yml"));
            settings = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), "settings.yml"));
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Failed initialise plugin files.");
        }
    }

    public void reloadFiles() {
        registerConfigurations();
    }

    public void saveFiles() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(plugin.getDataFolder(), "config.yml"));
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(messages, new File(plugin.getDataFolder(), "messages.yml"));
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(settings, new File(plugin.getDataFolder(), "settings.yml"));
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to save plugin files.");
        }
    }

    public Configuration getConfig() {
        return config;
    }

    public Configuration getMessages() {
        return messages;
    }

    public Configuration getSettings() {
        return settings;
    }

}
