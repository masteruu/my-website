package de.brueckner.fph.util;

public enum WeightSourceOption {

    OPTION_TCE("TCE"),
    OPTION_DOSING("Dosing"),
    OPTION_SCALE("Scale");

    private final String optionText;

    private WeightSourceOption(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
