package voidpointer.bukkit.respawnpoint.locale;

public interface LocaleMessage {
    /** Gets default path to locale message in locale storage. */
    String getPath();

    /**
     * Gets default value for message. Used if storage doesn't
     *      provide user defined value (usually it's undefined).
     */
    String getDefaultValue();
}
