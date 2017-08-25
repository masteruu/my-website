package de.brueckner.fph.util;

public enum NoteCausedByOption {

    /**
     * Caused By Options
     */
    OPTION_ELECTRIC("Electric", 1),
    OPTION_MECHANIC("Mechanic", 1),
    OPTION_PROCESS("Process", 1),
    OPTION_OPERATION("Operation", 1),
    OPTION_OTHER("Other", 1),
    OPTION_BRUCKNER("Brückner", 2),
    OPTION_CUSTOMER("Customer", 2),
    OPTION_SUBSUPPLIER("Subsupplier", 2),
    OPTION_OTHER2("Other", 2);

    private final String optionText;
    private final int dropdownIndex;

    private NoteCausedByOption(String optionText, int dropdownIndex) {
        this.optionText = optionText;
        this.dropdownIndex = dropdownIndex;
    }

    public String getOptionText() {
        return optionText;
    }

    public int getDropdownIndex() {
        return dropdownIndex;
    }
}
