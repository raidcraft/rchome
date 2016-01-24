package de.raidcraft.rchome;

import de.raidcraft.api.BasePlugin;
import de.raidcraft.api.config.ConfigurationBase;
import de.raidcraft.api.config.Setting;
import de.raidcraft.rchome.commands.RCHomeCommands;
import de.raidcraft.rchome.listener.PlayerListener;
import de.raidcraft.rchome.tables.TPlayerHome;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Philip
 */
public class RCHomePlugin extends BasePlugin {

    public LocalConfiguration config;

    @Override
    public void enable() {

        registerCommands(RCHomeCommands.class);
        registerEvents(new PlayerListener());
        reload();
    }

    @Override
    public void reload() {

        config = configure(new LocalConfiguration(this));
    }

    @Override
    public void disable() {

    }

    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> classes = new ArrayList<>();
        classes.add(TPlayerHome.class);
        return classes;
    }

    public LocalConfiguration getConfig() {

        return config;
    }

    public class LocalConfiguration extends ConfigurationBase<RCHomePlugin> {

        public LocalConfiguration(RCHomePlugin plugin) {

            super(plugin, "config.yml");
        }

        @Setting("home-max-radius") public int homeRadius = 200;
        @Setting("region-only-worlds") public String[] regionWorlds = new String[]{"world"};
    }
}
