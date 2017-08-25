package de.brueckner.fph.util;

public enum RollIdOption {

    OPTION_BMS_ID("BMS_ID"),
    OPTION_CUSTOMER_ID("CUSTOMER_ID"),
    OPTION_TECHNICAL_ID("TECHNICAL_ID");

    private final String optionText;

    private RollIdOption(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
