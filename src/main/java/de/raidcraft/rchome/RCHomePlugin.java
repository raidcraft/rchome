package de.raidcraft.rchome;

import de.raidcraft.api.BasePlugin;
import de.raidcraft.api.config.ConfigurationBase;

/**
 * @author Philip
 */
public class RCHomePlugin extends BasePlugin {

    private LocalConfiguration config;

    @Override
    public void enable() {

        reload();
    }

    @Override
    public void reload() {

        config = configure(new LocalConfiguration(this));
    }

    @Override
    public void disable() {

    }

    public class LocalConfiguration extends ConfigurationBase<RCHomePlugin> {

        public LocalConfiguration(RCHomePlugin plugin) {

            super(plugin, "config.yml");
        }
    }
}
