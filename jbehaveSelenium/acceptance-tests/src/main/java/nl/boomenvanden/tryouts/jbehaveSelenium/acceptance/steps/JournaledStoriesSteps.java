package nl.boomenvanden.tryouts.jbehaveSelenium.acceptance.steps;

import java.util.concurrent.TimeUnit;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.web.selenium.FirefoxWebDriverProvider;
import org.jbehave.web.selenium.WebDriverProvider;

public class JournaledStoriesSteps {

    private static final String JOURNAL_FIREFOX_COMMANDS = System.getProperty("JOURNAL_FIREFOX_COMMANDS", "false");
    private final WebDriverProvider webDriverProvider;

    public JournaledStoriesSteps(WebDriverProvider webDriverProvider) {
        this.webDriverProvider = webDriverProvider;
    }
    
//    @BeforeStories
//    public void beforeStories() throws Exception {
//        // Make sure the webdriver waits when needed for 5 seconds for an element which is not directly present
//        this.webDriverProvider.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//    }

    @AfterStories
    public void afterStories() throws Exception {

        if (!JOURNAL_FIREFOX_COMMANDS.equals("false") && webDriverProvider instanceof FirefoxWebDriverProvider) {
            FirefoxWebDriverProvider.WebDriverJournal journal = ((FirefoxWebDriverProvider) webDriverProvider).getJournal();
            System.out.println("Journal of WebDriver Commands:");
            for (Object entry : journal) {
                System.out.println(entry);
            }
            ((FirefoxWebDriverProvider) webDriverProvider).clearJournal();
        }

    }

}
