package de.brueckner.fph.util;

public enum TimeFormatLongOption {

    OPTION_WITH_COLON("HH:mm:ss");

    private final String optionText;

    private TimeFormatLongOption(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
