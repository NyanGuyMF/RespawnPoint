/*
 * This file is part of RespawnPoint Bukkit plug-in.
 *
 * RespawnPoint is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RespawnPoint is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with RespawnPoint. If not, see <https://www.gnu.org/licenses/>.
 */
package voidpointer.bukkit.respawnpoint.storage;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.val;
import voidpointer.bukkit.respawnpoint.RespawnPoint;

/** @author VoidPointer (aka NyanGuyMF) */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
final class YamlRespawnPoint implements RespawnPoint {
    private static final String DEFAULT_WORLD = "world";
    private static final double INEXISTANT_POSITION = Double.MAX_VALUE;

    private Location respawnLocation;

    public boolean saveToFile(final File yamlFile) {
        boolean isSaved = false;
        try {
            saveToFile0(yamlFile);
            isSaved = true;
        } catch (Exception ex) {
            handleSaveException(ex);
        }
        return isSaved;
    }

    private void saveToFile0(final File yamlFile) throws NoSuchLocationException, IOException {
        if (respawnLocation == null)
            throw new NoSuchLocationException("Unable to save: location is null");

        val config = YamlConfiguration.loadConfiguration(yamlFile);
        fillConfigWithLocation(respawnLocation, config);
        config.save(yamlFile);
    }

    private void fillConfigWithLocation(
            final Location respawnLocation, final YamlConfiguration config
    ) {
        config.set("world", respawnLocation.getWorld().getName());
        config.set("x", respawnLocation.getX());
        config.set("y", respawnLocation.getY());
        config.set("z", respawnLocation.getZ());
        config.set("yaw", respawnLocation.getYaw());
        config.set("pitch", respawnLocation.getPitch());
    }

    private void handleSaveException(final Exception ex) {
        new RespawnPointSaveException(ex).printStackTrace();
    }

    public void loadFromFile(final File yamlFile) {
        try {
            loadFromFile0(yamlFile);
        } catch (Exception ex) {
            handleLoadException(ex);
        }
    }

    private void loadFromFile0(final File yamlFile) throws Exception {
        YamlConfiguration respawnConfig = YamlConfiguration.loadConfiguration(yamlFile);
        Location respawnLocation = readLocationFromYaml(respawnConfig);

        if (respawnLocation != null)
            this.respawnLocation = LocationNormalizer.normalize(respawnLocation);
    }

    private Location readLocationFromYaml(final YamlConfiguration config) {
        try {
            return readLocationFromYaml0(config);
        } catch (NoSuchLocationException ex) {
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }

    private Location readLocationFromYaml0(final YamlConfiguration config)
            throws NoSuchLocationException {
        World world = readWorld(config);
        double posX = readPosition(config, ThreeDimensionPosition.X);
        double posY = readPosition(config, ThreeDimensionPosition.Y);
        double posZ = readPosition(config, ThreeDimensionPosition.Z);
        double yaw = readPosition(config, ThreeDimensionPosition.YAW);
        double pitch = readPosition(config, ThreeDimensionPosition.PITCH);

        Location loc = new Location(
            world, posX, posY,
            posZ, (float) yaw, (float) pitch
        );
        return loc;
    }

    /** Return world or null if specified or default not found. */
    private World readWorld(final YamlConfiguration config)
            throws NoSuchLocationException {
        String worldName = config.getString("world", DEFAULT_WORLD);
        World respawnWorld = Bukkit.getWorld(worldName);

        if (respawnWorld == null)
            throw new NoSuchLocationException("No such world: " + worldName);

        return respawnWorld;
    }

    private double readPosition(
            final YamlConfiguration config, final ThreeDimensionPosition pos
    ) throws NoSuchLocationException {
        double position = config.getDouble(pos.toString(), INEXISTANT_POSITION);

        if (position == INEXISTANT_POSITION) {
            String errorMessage = String.format("No such %s coordinate", pos);
            throw new NoSuchLocationException(errorMessage);
        }

        return position;
    }

    private void handleLoadException(final Exception ex) {
        new RespawnPointLoadingException(ex).printStackTrace();
    }

    @Override public String toString() {
        return String.format(
            "YamlRespawnPoint = [x=%.2f, y=%.2f, z=%.2f, yaw=%.1f, pitch=%.1f]",
            respawnLocation.getX(),
            respawnLocation.getY(),
            respawnLocation.getZ(),
            respawnLocation.getYaw(),
            respawnLocation.getPitch()
        );
    }
}
