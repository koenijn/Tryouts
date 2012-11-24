/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.jbehaveSelenium.acceptance.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import static org.openqa.selenium.By.*;
import org.seleniumhq.selenium.fluent.Period;

/**
 *
 * @author mark
 */
public class Store extends FluentWebDriverPage {

    public enum IpadColor {
        WHITE,
        BLACK
    }
    
    public Store(WebDriverProvider driverProvider) {
        super(driverProvider);
    }
    
    public void shopForIPadThroughProductShelf() {
//        findElement(xpath("//*[@class='product-shelf-item shop_ipad']"))
//                .findElement(tagName("a"))
//                .click();
        link(cssSelector(".product-shelf li.shop_ipad a.product-shelf-link")).click();
    }
    
    public void chooseIPadWithRetinaDisplay() {
        link(cssSelector(".dept-shelf li.three a.product-shelf-link")).click();
    }
    
    public void chooseStepOneOption(IpadColor color) {
        int optionNumber;
        
        switch(color) {
            case BLACK: 
                optionNumber = 1;
                break;
            case WHITE: 
                optionNumber = 2;
                break;
            default:
                throw new IllegalArgumentException("This should not happen");
        }
        span(cssSelector("#product-selection-1 li.option-" + optionNumber + " span.clearfix")).click();
    }
    
    public void chooseStepTwoOption() {
        int optionNumber = 2;
        //"IPAD2012-4GEN_4G option-2 active"
        span(cssSelector("#product-selection-2 li.IPAD2012-4GEN_4G.option-" + optionNumber + " span.model-spec"))
                .click();
    }
    
    public void chooseStepThreeOption() {
        span(cssSelector("div.carrier-select.expanded li.option-1 span.shipping"))
                .click();
    }
    
}
