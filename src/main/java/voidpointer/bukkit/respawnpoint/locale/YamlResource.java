/*
 * This file is part of RespawnPoint Bukkit, Spigot and PaperSpigot compatable plug-in.
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
package voidpointer.bukkit.respawnpoint.locale;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** @author NyanGuyMF - Vasiliy Bely */
@Getter
@Setter
@AllArgsConstructor
final class YamlResource implements PluginResource {
    private final String targetFilename;
    private final String pluginResourceName;
    private PluginResource alternativeResource;

    public YamlResource(final String filename) {
        pluginResourceName = targetFilename = filename;
        alternativeResource = null;
    }
}
