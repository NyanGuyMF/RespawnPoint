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
package voidpointer.bukkit.respawnpoint.storage;

import org.bukkit.Location;

/** @author VoidPointer (aka NyanGuyMF) */
final class LocationNormalizer {
    protected static Location normalize(final Location loc) {
        final Location normalized = loc.clone();

        normalized.setX(normalizeCoordinate(normalized.getX()));
        normalized.setZ(normalizeCoordinate(normalized.getZ()));
        normalized.setY(normalized.getBlockY());
        normalized.setYaw(normalizeYaw(normalized.getYaw()));
        normalized.setPitch(normalizePitch(normalized.getPitch()));

        return normalized;
    }

    private static double normalizeCoordinate(final double coord) {
        double integerPath = coord-(coord%1);
        double normalized = (integerPath >= 0) ? (integerPath + .5D) : (integerPath - .5D);
        return normalized;
    }

    private static float normalizeYaw(final float yaw) {
        return (Math.round(yaw / 10F) * 10F);
    }

    private static float normalizePitch(final float pitch) {
        return 0F;
    }
}
