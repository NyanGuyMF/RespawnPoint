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

import lombok.Getter;

/** @author NyanGuyMF - Vasiliy Bely */
@Getter
final class LocaleResource implements PluginResource {
    public static final String LOCALE_EXTENSION = "yml";
    public static final String LOCALE_FILENAME = "messages";

    private final String targetFilename;
    private final String pluginResourceName;
    private final PluginResource alternativeResource;

    public LocaleResource(final LocaleLanguage locale) {
        pluginResourceName = targetFilename = languageToFilename(locale.getLanguage());
        if (!locale.isDefault())
            alternativeResource = new LocaleResource(LocaleLanguage.DEFAULT);
        else
            alternativeResource = null;
    }

    private String languageToFilename(final String language) {
        return String.format("%s_%s.%s", LOCALE_FILENAME, language, LOCALE_EXTENSION);
    }
}
