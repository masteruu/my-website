package de.brueckner.fph.util;

/**
 * Options for date format long drop down
 * <p>
 * Created by andreeag on 7/13/2017.
 */
public enum DateFormatLongOption {

    OPTION_WITH_POINT("dd.MM.yyyy"),
    OPTION_WITH_SLASH("MM/dd/yyyy"),
    OPTION_WITH_DASH("yyyy-MM-dd");

    private final String optionText;

    private DateFormatLongOption(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
