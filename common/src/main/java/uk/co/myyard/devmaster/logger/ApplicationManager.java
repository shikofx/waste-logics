package uk.co.myyard.devmaster.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.myyard.devmaster.browser.BrowserManager;
import uk.co.myyard.devmaster.page.InvoicePage;


public class ApplicationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationManager.class);

    public ApplicationManager() {
        BrowserManager.browser()
                .setTimeoutForElement(5);
    }

    public void goToTestPage() {
        String titlePageUrl = BrowserManager.browser()
                .getProperties().getTestPageUrl();
        LOGGER.info(String.format("->> Go to the Test page: %s.", titlePageUrl));
        BrowserManager.browser().open(titlePageUrl);
    }

    public void clear() {
        LOGGER.info("->> STOP browser and remove driver");
        BrowserManager.browser()
                .clearCookies();
    }

    public void stop() {
        BrowserManager.browser()
                .destroy();
    }

    public void maximize() {
        BrowserManager.browser().maximizeWindow();
    }

    public InvoicePage getTestPage() {
        return new InvoicePage();
    }
}
