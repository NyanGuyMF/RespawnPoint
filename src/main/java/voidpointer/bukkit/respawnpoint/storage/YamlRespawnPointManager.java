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

import org.bukkit.Location;
import org.bukkit.entity.Player;

import lombok.val;
import voidpointer.bukkit.respawnpoint.RespawnPoint;
import voidpointer.bukkit.respawnpoint.RespawnPointManager;

/** @author VoidPointer (aka NyanGuyMF) */
public final class YamlRespawnPointManager implements RespawnPointManager {
    private static final String YAML_EXTENSION = "yml";

    private final Location defaultRespawnLocation;
    private File dataFolder;

    public YamlRespawnPointManager(
            final Location defaultRespawnLocation, final File dataFolder
    ) {
        this.defaultRespawnLocation = LocationNormalizer.normalize(defaultRespawnLocation);
        this.dataFolder = dataFolder;
    }

    @Override public boolean saveRespawnPoint(
            final Player player, final Location newRespawnLocation
    ) {
        val yamlPoint = new YamlRespawnPoint();
        yamlPoint.setRespawnLocation(LocationNormalizer.normalize(newRespawnLocation));
        return yamlPoint.saveToFile(getRespawnPointFile(player));
    }

    @Override public RespawnPoint load(final Player player) {
        File respawnPointFile = getRespawnPointFile(player);

        val respawnPoint = new YamlRespawnPoint();
        if (respawnPointFile.exists())
            respawnPoint.loadFromFile(respawnPointFile);
        else
            respawnPoint.setRespawnLocation(defaultRespawnLocation);

        return respawnPoint;
    }

    private File getRespawnPointFile(final Player player) {
        return new File(dataFolder, getPlayerFilename(player));
    }

    private String getPlayerFilename(final Player player) {
        return String.format("%s.%s", player.getName(), YAML_EXTENSION);
    }
}
