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

import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Implementation for {@link Locale} interface using
 *      yaml config library with {@link ConfigLoader}.
 */
final class YamlLocale implements Locale {
    public static final char ALT_COLOR_CHAR = '&';

    private File storageFile;
    private YamlConfiguration localeStorage;

    /** Creates an empty locale storage. */
    public YamlLocale() {
        localeStorage = new YamlConfiguration();
    }

    public YamlLocale(final String storageFilename) {
        this(new File(storageFilename));
    }

    /**
     * Creates new locale storage instance for specified file with
     *      locale contents.
     *
     * @param   storageFile     File with all localized messages.
     */
    public YamlLocale(final File storageFile) {
        this.storageFile = storageFile;
    }

    public boolean load() {
        try {
            localeStorage = loadYamlStorage0();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private YamlConfiguration loadYamlStorage0() throws IllegalArgumentException {
        return YamlConfiguration.loadConfiguration(storageFile);
    }

    @Override
    public String getColorizedMessage(final LocaleMessage message, final Object... messageArgs) {
        return getLocalizedMessage(message).insertArguments(messageArgs).colorize().toString();
    }

    @Override
    public String getMessage(final LocaleMessage message, final Object... messageArgs) {
        return getLocalizedMessage(message).insertArguments(messageArgs).toString();
    }

    private LocaleMessageBuffer getLocalizedMessage(final LocaleMessage message) {
        if (localeStorage == null)
            throw new IllegalStateException("Configuration is not loaded");

        String localizedMessage;
        if (localeStorage.isSet(message.getPath()))
            localizedMessage = localeStorage.getString(message.getPath());
        else
            localizedMessage = message.getDefaultValue();

        return new LocaleMessageBuffer(localizedMessage);
    }
}
