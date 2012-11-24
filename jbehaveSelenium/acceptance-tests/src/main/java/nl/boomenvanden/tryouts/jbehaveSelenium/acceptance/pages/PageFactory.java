package nl.boomenvanden.tryouts.jbehaveSelenium.acceptance.pages;

import org.jbehave.web.selenium.WebDriverProvider;

public class PageFactory {

    private final WebDriverProvider webDriverProvider;

    public PageFactory(WebDriverProvider webDriverProvider) {
        this.webDriverProvider = webDriverProvider;
    }

    public Home newHome() {
        return new Home(webDriverProvider);
    }

    public Site newSite() {
        return new Site(webDriverProvider);
    }

    public Store newStore() {
        return new Store(webDriverProvider);
    }

}
