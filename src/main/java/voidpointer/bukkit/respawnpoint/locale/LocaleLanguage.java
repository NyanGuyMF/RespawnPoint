package voidpointer.bukkit.respawnpoint.locale;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LocaleLanguage {
    RUSSIAN("ru"),
    ENGLISH("en"),
    DEFAULT(RUSSIAN.getLanguage());

    private final String language;

    public static LocaleLanguage fromString(String languageString) {
        if (languageString == null)
            return LocaleLanguage.DEFAULT;

        languageString = languageString.toLowerCase();
        for (LocaleLanguage language : values())
            if (languageString.startsWith(language.getLanguage()))
                    return language;

        return DEFAULT;
    }

    public boolean isRussian() {
        return this == RUSSIAN;
    }

    public boolean isEnglish() {
        return this == ENGLISH;
    }

    public boolean isDefault() {
        return this == DEFAULT;
    }
}
