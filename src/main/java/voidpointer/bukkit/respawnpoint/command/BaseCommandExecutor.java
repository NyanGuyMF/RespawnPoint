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

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permissible;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import voidpointer.bukkit.respawnpoint.ErrorMessage;
import voidpointer.bukkit.respawnpoint.locale.Locale;

/** @author VoidPointer (aka NyanGuyMF) */
@AllArgsConstructor
abstract class BaseCommandExecutor implements CommandExecutor {
    @Getter(value=AccessLevel.PROTECTED)
    private final Locale locale;

    @Override public boolean onCommand(
            final CommandSender sender, final Command command,
            final String label, final String[] rawArgs
    ) {
        CommandArgs args = new BaseCommandArgs(sender, rawArgs);

        boolean isCancelled = false;
        if (isCancelled = !hasPermission(sender)) {
            locale.sendMessage(sender, ErrorMessage.NO_PERMISSION);
        } else if (isCancelled = (isPlayerRequired() && !args.isPlayer())) {
            locale.sendMessage(sender, ErrorMessage.PLAYER_IS_REQUIRED);
        }

        if (!isCancelled)
            execute(args);
        return true;
    }

    protected boolean hasPermission(final Permissible permissible) {
        return true;
    }

    protected boolean isPlayerRequired() {
        return false;
    }

    protected abstract void execute(CommandArgs args);

    public void register(final JavaPlugin plugin) {
        plugin.getCommand(getName()).setExecutor(this);
    }

    protected abstract String getName();
}
