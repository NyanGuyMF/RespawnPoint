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

import org.bukkit.command.CommandSender;

/** Represents default Bukkit locale storage with colorized messages. */
public interface Locale {
    /**
     * Get message from a locale storage.
     *
     * @param   path            Abstract path to message to find it in the storage.
     * @param   messageArgs     List of arguments where each argument is
     *      associated with "{N}" substring placeholder in localized message.
     *      <i> * where N is positive integer and zero. *</i>
     *
     * @return  Raw string message with inserted message arguments.
     */
    String getMessage(LocaleMessage path, Object... messageArgs);

    /**
     * Get message from a locale storage.
     *
     * @param   path    Abstract path to message to find it in the storage.
     *
     * @return  Raw message.
     */
    default String getMessage(final LocaleMessage path) {
        return getMessage(path, new Object[0]);
    }

    /**
     * Get message from locale storage and converts color symbols
     *  into conventional Bukkit color codes.
     *
     * @param   path            Abstract path to message to find it in storage.
     * @param   messageArgs     List of arguments where each argument is
     *      associated with "{N}" substring placeholder in localized message.
     *      <i> * where N is positive integer and zero. *</i>
     *
     * @return  Colorized message with inserted message arguments.
     */
    String getColorizedMessage(LocaleMessage path, Object... messageArgs);

    /**
     * Get message from locale storage and convert color symbols
     *  into conventional Bukkit color codes.
     *
     * @param   path    Abstract path to message to find it in storage.
     *
     * @return  Colorized message.
     */
    default String getColorizedMessage(final LocaleMessage path) {
        return getColorizedMessage(path, new Object[0]);
    }

    default void sendMessage(final CommandSender sender, final LocaleMessage path) {
        sender.sendMessage(getColorizedMessage(path));
    }
}
