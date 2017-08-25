package de.brueckner.fph.managers;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class that handles different actions that need to be performed on the left and right menus
 */
public class MenuManager {

    private static final Logger logger = LoggerFactory.getLogger(MenuManager.class);

    private WebDriver driver;

    public MenuManager(WebDriver driver) {
        this.driver = driver;
    }


}
