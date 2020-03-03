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
package voidpointer.bukkit.respawnpoint.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

import lombok.AllArgsConstructor;
import voidpointer.bukkit.respawnpoint.RespawnPoint;
import voidpointer.bukkit.respawnpoint.RespawnPointManager;

/** @author VoidPointer (aka NyanGuyMF) */
@AllArgsConstructor
public final class PlayerRespawnListener implements Listener {
    private RespawnPointManager respawnPointManager;

    @EventHandler public void onPlayerRespawn(final PlayerRespawnEvent respawnEvent) {
        Player player = respawnEvent.getPlayer();
        RespawnPoint respawnLocation = respawnPointManager.load(player);
        respawnEvent.setRespawnLocation(respawnLocation.getRespawnLocation());
    }

    public void register(final Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
