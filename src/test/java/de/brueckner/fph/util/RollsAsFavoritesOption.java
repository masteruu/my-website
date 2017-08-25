package de.brueckner.fph.util;

public enum RollsAsFavoritesOption {

    OPTION_FAVORITE("favorite"),
    OPTION_NORMAL("normal"),
    OPTION_BENCHMARK("benchmark");

    private final String optionText;

    private RollsAsFavoritesOption(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
