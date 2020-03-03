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
package voidpointer.bukkit.respawnpoint.locale;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.AccessLevel;
import lombok.Getter;

/** @author VoidPointer (aka NyanGuyMF) */
public class LocalizedPlugin extends JavaPlugin {
    @Getter(value=AccessLevel.PROTECTED)
    private Locale locale;

    private PluginResourceBootstrapper resourceBoot;

    @Override public void onLoad() {
        resourceBoot = new PluginResourceBootstrapper(this);
        loadConfiguration();
        if (locale == null)
            return;
    }

    protected String ensurePluginResourceExists(final PluginResource resource) {
        return resourceBoot.ensureFileResourceExist(resource);
    }

    private void loadConfiguration() {
        ensurePluginResourceExists(new YamlResource("config.yml"));

        PluginResource localeResource = new LocaleResource(getPluginLanguage());
        String localeFilename = ensurePluginResourceExists(localeResource);
        if (localeFilename != null)
            locale = loadLocaleFromFile(localeFilename);
        else
            locale = loadEmptyLocale();
    }

    private LocaleLanguage getPluginLanguage() {
        return LocaleLanguage.fromString(super.getConfig().getString(
            "lang",
            LocaleLanguage.DEFAULT.getLanguage()
        ));
    }

    private Locale loadLocaleFromFile(final String localeFilename) {
        File localeFile = new File(super.getDataFolder(), localeFilename);
        YamlLocale yamlLocale = new YamlLocale(localeFile);
        yamlLocale.load();
        return yamlLocale;
    }

    private Locale loadEmptyLocale() {
        return new YamlLocale();
    }
}
