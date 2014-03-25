package de.raidcraft.rchome;

import de.raidcraft.api.BasePlugin;
import de.raidcraft.api.config.ConfigurationBase;
import de.raidcraft.api.config.Setting;
import de.raidcraft.rchome.commands.RCHomeCommands;
import de.raidcraft.rchome.listener.PlayerListener;
import de.raidcraft.rchome.tables.PlayerHomesTable;

/**
 * @author Philip
 */
public class RCHomePlugin extends BasePlugin {

    public LocalConfiguration config;

    @Override
    public void enable() {

        registerCommands(RCHomeCommands.class);
        registerEvents(new PlayerListener());
        registerTable(PlayerHomesTable.class, new PlayerHomesTable());
        reload();
    }

    @Override
    public void reload() {

        config = configure(new LocalConfiguration(this));
    }

    @Override
    public void disable() {

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
