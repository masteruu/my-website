package de.brueckner.fph.util;

public enum TreeFilterOptions {

    OPTION_ALL_STATES("All States"),
    OPTION_NON_PRODUCTIVE("Non Productive"),
    OPTION_INVALID("Invalid"),
    OPTION_ALL_ROLLS("All Rolls"),
    OPTION_GOOD_ROLLS("Good Rolls"),
    OPTION_WITH_LAB_DATA("With Lab Data"),
    OPTION_ALL_BAD_ROLLS("All Bad Rolls"),
    OPTION_RECYCLABLE_ROLLS("Recyclable Rolls"),
    OPTION_NON_RECYCLABLE_ROLLS("Non Recyclable Rolls");

    private final String optionText;

    private TreeFilterOptions(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
