package voidpointer.bukkit.respawnpoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import voidpointer.bukkit.respawnpoint.locale.LocaleMessage;

@AllArgsConstructor
public enum ErrorMessage implements LocaleMessage {
    PLAYER_IS_REQUIRED("&cOnly player can perform this command.&r"),
    RESPAWN_LOCATION_ISNT_SET("&cUnable to save respawn location.&r"),
    NO_PERMISSION("&cYou have no permission for this action.&r"),
    ;

    private static final String PREFIX = "error";

    @Getter private final String defaultValue;

    @Override public String getPath() {
        return String.format("%s.%s", PREFIX, toString().replace('_', '-')).toLowerCase();
    }
}
