package de.brueckner.fph.util;

public enum NoteMachinePartOption {

    /**
     * Machine part options
     */
    OPTION_CAS("CAS"),
    OPTION_CRN("CRN"),
    OPTION_DIE("DIE"),
    OPTION_DOS("DOS"),
    OPTION_EXT("EXT"),
    OPTION_FIL("FIL"),
    OPTION_MDO("MDO");

    private final String optionText;

    private NoteMachinePartOption(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
