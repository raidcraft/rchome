package de.raidcraft.rchome.tables;

import de.raidcraft.RaidCraft;
import de.raidcraft.api.database.Table;

import java.sql.SQLException;

/**
 * @author Philip
 */
public class PlayerHomesTable extends Table {

    public PlayerHomesTable() {

        super("player_homes", "rchome_");
    }

    @Override
    public void createTable() {

        try {
            getConnection().prepareStatement(
                    "CREATE TABLE `" + getTableName() + "` (\n" +
                            "`player` VARCHAR( 32 ) NOT NULL ,\n" +
                            "`id` INT NOT NULL AUTO_INCREMENT ,\n" +
                            "`x` DOUBLE NOT NULL ,\n" +
                            "`y` DOUBLE NOT NULL ,\n" +
                            "`z` DOUBLE NOT NULL ,\n" +
                            "`pitch` FLOAT NOT NULL ,\n" +
                            "`yaw` FLOAT NOT NULL ,\n" +
                            "`world` VARCHAR( 32 ) NOT NULL ,\n" +
                            "PRIMARY KEY ( `id` )\n" +
                            ")").execute();
        } catch (SQLException e) {
            RaidCraft.LOGGER.warning(e.getMessage());
        }
    }
}
