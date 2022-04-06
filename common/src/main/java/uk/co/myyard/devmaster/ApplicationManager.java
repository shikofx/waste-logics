package uk.co.myyard.devmaster;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.myyard.devmaster.browser.BrowserManager;
import uk.co.myyard.devmaster.page.InvoicePage;


public class ApplicationManager  {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationManager.class);

    public ApplicationManager() {
        BrowserManager.browser()
                .setTimeoutForElement(5);
    }

    public ApplicationManager goToTestPage() {
        String pageUrl = BrowserManager.browser().getProperties().getTestPageUrl();
        LOGGER.info(String.format("->> Go to the Invoice page: %s.", pageUrl));
        BrowserManager.browser()
                .open(pageUrl);
        return this;
    }

    public void stop() {
        BrowserManager.browser().destroy();
    }

    public ApplicationManager maximize() {
        BrowserManager.browser().maximizeWindow();
        return this;
    }

    public InvoicePage getInvoicePage() {
        return new InvoicePage();
    }

}
