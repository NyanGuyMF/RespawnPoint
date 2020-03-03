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
package voidpointer.bukkit.respawnpoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import voidpointer.bukkit.respawnpoint.locale.LocaleMessage;

/** @author NyanGuyMF - Vasiliy Bely */
@AllArgsConstructor
public enum InfoMessage implements LocaleMessage {
    RESPAWN_LOCATION_SET("&eRespawn location has been successfully set.&r"),
    ;

    private static final String PREFIX = "info";

    @Getter private final String defaultValue;

    @Override public String getPath() {
        return String.format("%s.%s", PREFIX, toString().replace('_', '-')).toLowerCase();
    }
}
