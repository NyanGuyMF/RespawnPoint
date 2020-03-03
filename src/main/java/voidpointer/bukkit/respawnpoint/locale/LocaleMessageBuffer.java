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

import static org.bukkit.ChatColor.translateAlternateColorCodes;

import lombok.Getter;

/**
 * Help class that incapsulates message transformation algorithms.
 */
final class LocaleMessageBuffer {
    public static final char DEFAULT_COLOR_CHAR = '&';

    @Getter private String localizedMessage;

    public LocaleMessageBuffer(final String localizedMessage) {
        this.localizedMessage = localizedMessage;
    }

    /**
     * Manually replace "{N}" char sequence in message with
     *      given object.toString(), where N is an object index.
     *
     * @param   arguments   Array of objects to insert into
     *      localized string.
     */
    public LocaleMessageBuffer insertArguments(final Object... arguments) {
        StringBuffer buffer =  new StringBuffer(localizedMessage);
        for (int index = 0; index < arguments.length; index++) {
            String placeholder = "{" + index + "}";
            String replacement = arguments[index].toString();

            int placeholderOccurrence, from, to;
            // replace all placeholder occurrences with appropriate replacement
            while ((placeholderOccurrence = buffer.indexOf(placeholder)) != -1) {
                from = placeholderOccurrence;
                to = from + placeholder.length();
                buffer.replace(from, to, replacement);
            }
        }
        localizedMessage = buffer.toString();
        return this;
    }

    /**
     * Transforms special color character into conventioned
     *      Bukkit colors.
     */
    public LocaleMessageBuffer colorize() {
        colorize(DEFAULT_COLOR_CHAR);
        return this;
    }

    /**
     * Transforms special color character into conventioned
     *      Bukkit colors.
     *
     * @param   altColorChar    Character to replace with
     *      conventioned Bukkit color char.
     */
    public LocaleMessageBuffer colorize(final char altColorChar) {
        localizedMessage = translateAlternateColorCodes(altColorChar, localizedMessage);
        return this;
    }

    @Override public String toString() {
        return localizedMessage;
    }
}
