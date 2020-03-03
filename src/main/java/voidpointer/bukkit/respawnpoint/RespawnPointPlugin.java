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
package voidpointer.bukkit.respawnpoint;

import org.bukkit.World;

import voidpointer.bukkit.respawnpoint.command.SetRespawnPointCommandExecutor;
import voidpointer.bukkit.respawnpoint.listener.PlayerRespawnListener;
import voidpointer.bukkit.respawnpoint.locale.LocalizedPlugin;
import voidpointer.bukkit.respawnpoint.storage.YamlRespawnPointManager;

/** @author VoidPointer (aka NyanGuyMF) */
public class RespawnPointPlugin extends LocalizedPlugin {
    private static final int FIRST_WORLD_INDEX = 0;

    private RespawnPointManager respawnPointManager;

    @Override public void onLoad() {
        super.onLoad();
    }

    @Override public void onEnable() {
        respawnPointManager = new YamlRespawnPointManager(
            getDefaultWorld().getSpawnLocation(), super.getDataFolder()
        );
        registerCommands();
        registerListeners();
    }

    private World getDefaultWorld() {
        return super.getServer().getWorlds().get(FIRST_WORLD_INDEX);
    }

    private void registerCommands() {
        new SetRespawnPointCommandExecutor(super.getLocale(), respawnPointManager)
            .register(this);
    }

    private void registerListeners() {
        new PlayerRespawnListener(respawnPointManager).register(this);
    }
}
