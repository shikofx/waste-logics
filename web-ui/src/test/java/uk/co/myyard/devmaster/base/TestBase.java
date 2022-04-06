package uk.co.myyard.devmaster.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import uk.co.myyard.devmaster.ApplicationManager;

public class TestBase {

    public static ApplicationManager bot;

    @BeforeAll
    static void initApplication() {
        bot = new ApplicationManager()
                .maximize()
                .goToTestPage();
    }

    @BeforeEach
    void setUp() {

    }

    @AfterAll
    static void stop() {
        bot.stop();
    }
}
