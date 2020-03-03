/*
 * This file is part of TemporalNicknameWhitelist Bukkit, Spigot and PaperSpigot compatable plug-in.
 *
 * TemporalNicknameWhitelist is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TemporalNicknameWhitelist is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TemporalNicknameWhitelist. If not, see <https://www.gnu.org/licenses/>.
 */
package voidpointer.bukkit.respawnpoint.locale;

import java.io.File;

import org.bukkit.plugin.Plugin;

import lombok.AllArgsConstructor;

/** @author NyanGuyMF - Vasiliy Bely */
@AllArgsConstructor
final class PluginResourceBootstrapper {
    private static final boolean REPLACE = true;
    private Plugin pluginOwner;

    /**
     * @return Final existent resource filename or null.
     */
    public String ensureFileResourceExist(final PluginResource resource) {
        if (fileExists(resource.getTargetFilename()))
            return resource.getTargetFilename();

        ensureParentExists(resource.getTargetFilename());
        if (saveResource(resource.getPluginResourceName()))
            return resource.getTargetFilename();

        if (resource.getAlternativeResource() != null)
            return ensureFileResourceExist(resource.getAlternativeResource());

        return null;
    }

    private boolean fileExists(final String filename) {
        return new File(pluginOwner.getDataFolder(), filename).exists();
    }

    private boolean saveResource(final String resourcePath) {
        try {
            saveResource0(resourcePath);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    private void saveResource0(final String resourceName) throws IllegalArgumentException {
        pluginOwner.saveResource(resourceName, REPLACE);
    }

    private void ensureParentExists(final String resourceName) {
        File parentDirectory = new File(pluginOwner.getDataFolder(), resourceName).getAbsoluteFile().getParentFile();
        if (!parentDirectory.exists())
            parentDirectory.mkdirs();
    }
}
