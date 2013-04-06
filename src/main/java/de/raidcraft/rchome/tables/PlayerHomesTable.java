package de.raidcraft.rchome.tables;

import de.raidcraft.RaidCraft;
import de.raidcraft.api.database.Table;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
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
                            "`id` INT NOT NULL AUTO_INCREMENT ,\n" +
                            "`player` VARCHAR( 32 ) NOT NULL ,\n" +
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

    public Location getHome(Player player) {

        try {
            ResultSet resultSet = getConnection().prepareStatement(
                    "SELECT * FROM " + getTableName() + " WHERE player = '" + player.getName() + "' " +
                            "AND world = '" + player.getLocation().getWorld().getName() + "';").executeQuery();

            while (resultSet.next()) {

                return new Location(player.getWorld(),
                        resultSet.getDouble("x"),
                        resultSet.getDouble("y"),
                        resultSet.getDouble("z"),
                        resultSet.getFloat("yaw"),
                        resultSet.getFloat("pitch"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setHome(Player player) {

        deleteHome(player);
        try {
            getConnection().prepareStatement("INSERT INTO " + getTableName() + " (player, x, y, z, pitch, yaw, world) " +
                    "VALUES (" +
                    "'" + player.getName() + "'" + "," +
                    "'" + player.getLocation().getX() + "'" + "," +
                    "'" + player.getLocation().getY() + "'" + "," +
                    "'" + player.getLocation().getZ() + "'" + "," +
                    "'" + player.getLocation().getPitch() + "'" + "," +
                    "'" + player.getLocation().getYaw() + "'" + "," +
                    "'" + player.getLocation().getWorld().getName() + "'" +
                    ");").executeUpdate();
        } catch (SQLException e) {
            RaidCraft.LOGGER.warning(e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteHome(Player player) {

        try {
            getConnection().prepareStatement(
                    "DELETE FROM " + getTableName() + " WHERE player = '" + player.getName() + "' " +
                            "AND world = '" + player.getLocation().getWorld().getName() + "';").execute();
        } catch (SQLException e) {
            RaidCraft.LOGGER.warning(e.getMessage());
            e.printStackTrace();
        }
    }
}
