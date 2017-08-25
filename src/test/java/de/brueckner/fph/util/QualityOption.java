package de.brueckner.fph.util;

public enum QualityOption {

    OPTION_GOOD_ROLL("Good Roll"),
    OPTION_NON_RECYCLABLE("Non-Recyclable"),
    OPTION_PENDING("Pending"),
    OPTION_RECYCLABLE("Recyclable"),
    OPTION_LABORATORY("Laboratory"),
    OPTION_VISUAL("Visual");

    private final String optionText;

    private QualityOption(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
