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

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;

import voidpointer.bukkit.respawnpoint.ErrorMessage;
import voidpointer.bukkit.respawnpoint.InfoMessage;
import voidpointer.bukkit.respawnpoint.RespawnPointManager;
import voidpointer.bukkit.respawnpoint.locale.Locale;

/** @author VoidPointer (aka NyanGuyMF) */
public final class SetRespawnPointCommandExecutor extends BaseCommandExecutor {
    private static final String COMMAND_NAME = "setrespawnpoint";
    private static final String COMMAND_PERMISSION = "respawnpoint.set";

    private RespawnPointManager respawnPointManager;

    public SetRespawnPointCommandExecutor(
            final Locale locale, final RespawnPointManager respawnPointManager
    ) {
        super(locale);
        this.respawnPointManager = respawnPointManager;
    }

    @Override protected void execute(final CommandArgs args) {
        Player player = args.getPlayer();
        Location newRespawnLocation = player.getLocation();
        boolean isSaved = respawnPointManager.saveRespawnPoint(player, newRespawnLocation);

        if (isSaved)
            super.getLocale().sendMessage(player, InfoMessage.RESPAWN_LOCATION_SET);
        else
            super.getLocale().sendMessage(player, ErrorMessage.RESPAWN_LOCATION_ISNT_SET);
    }

    @Override protected boolean hasPermission(final Permissible permissible) {
        return permissible.hasPermission(COMMAND_PERMISSION);
    }

    @Override protected boolean isPlayerRequired() {
        return true;
    }

    @Override protected String getName() {
        return COMMAND_NAME;
    }
}
