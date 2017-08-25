package de.brueckner.fph.util;

public enum LoginCredentials {

    USER_VIEW("gr_view_fph_test", "test"),
    USER_OP("Gr_op_fph_test", "test"),
    USER_SUP("Gr_sup_fph_test", "test"),
    USER_CT("Gr_ct_fph_test", "test"),
    USER_CE("Gr_ce_fph_test", "test"),
    USER_ET("Gr_et_fph_test", "test"),
    USER_EE("Gr_ee_fph_test", "test"),
    USER_DEV("Gr_dev_fph_test", "test");

    private final String username;
    private final String password;

    private LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
