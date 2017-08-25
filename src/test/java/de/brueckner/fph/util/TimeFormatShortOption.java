package de.brueckner.fph.util;

public enum TimeFormatShortOption {

    OPTION_WITH_COLON("HH:mm");

    private final String optionText;

    private TimeFormatShortOption(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
