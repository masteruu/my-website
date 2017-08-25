package de.brueckner.fph.util;

/**
 * Options for date format short drop down
 * <p>
 * Created by andreeag on 7/13/2017.
 */
public enum DateFormatShortOption {

    OPTION_WITH_POINT("dd.MM.yy"),
    OPTION_WITH_SLASH("MM/dd/yy"),
    OPTION_WITH_DASH("yy-MM-dd");

    private final String optionText;

    private DateFormatShortOption(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
