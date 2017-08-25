package de.brueckner.fph.acceptance.userInterface;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class LoginAllGoodUsersAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginAllGoodUsersAcceptanceTest.class);

    @Test //check log in for all users
    public void loginAllGoodUsersAcceptance() {
        logger.info("loginAllGoodUsersAcceptance");

        for (LoginCredentials credentials : LoginCredentials.values()) {
            login(credentials);
        }
    }

    private void login(LoginCredentials credential) {
        applicationManager.loginPage(credential.getUsername(), credential.getPassword());
        applicationManager.logout();
    }
}