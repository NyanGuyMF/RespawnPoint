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
package voidpointer.bukkit.respawnpoint.command;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/** @author VoidPointer (aka NyanGuyMF) */
public interface CommandArgs {
    default boolean isPlayer() {
        return (getSender() instanceof Player);
    }

    Player getPlayer();

    CommandSender getSender();

    List<String> getArgs();
}
