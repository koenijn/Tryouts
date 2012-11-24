/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.jbehaveSelenium.acceptance.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import static org.openqa.selenium.By.*;

/**
 *
 * @author mark
 */
public class Home extends FluentWebDriverPage {

    public Home(WebDriverProvider driverProvider) {
        super(driverProvider);
    }
    
    public void go() {
        get("http://www.apple.com");
    }
    
    public void openStore() {
        link(linkText("Store")).click();
    }
    
}
