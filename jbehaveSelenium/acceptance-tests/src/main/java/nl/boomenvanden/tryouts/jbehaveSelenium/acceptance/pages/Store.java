/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.jbehaveSelenium.acceptance.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import static org.openqa.selenium.By.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

        switch (color) {
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
        span(cssSelector("#product-selection-2 li.IPAD2012-4GEN_4G.option-" + optionNumber + " span.model-spec")).click();
    }

    public void chooseStepThreeOption() throws InterruptedException {
        WebDriver driver = getDriverProvider().get();
        WebDriverWait wait = new WebDriverWait(driver, 5);

        wait.until(ExpectedConditions.elementToBeClickable(cssSelector("div.carrier-select ul li.option-1.att label span"))).click();
    }

    public void summaryProductIs(String product) {
        h3(cssSelector("div.content-summary-body div.section.section-product-title h3")).getText().shouldBe(product);
    }

    public void summaryPriceIs(String price) {
        span(cssSelector("div.content-summary-body span.current_price span span")).getText().shouldContain(price);
    }

    public void continueFromSummary() {
        button(cssSelector("div.content-summary-body li.add-to-cart div.form-submit-btn button.button")).click();
    }

    public void skipEngraving() {
        button(cssSelector("div#engraving-options fieldset.purchase-info.skip-engraving button.button")).click();
    }

    public void addToOrder() {
        button(cssSelector("ul.purchase-info li.add-to-cart button.button")).click();
    }

    public void cartTotalPriceIs(String price) {
        span(id("cart-summary-order-total-value")).getText().shouldContain(price);
    }

    public void overviewProductIs(String productLine) {
        link(cssSelector("div.content.product-info.pvm h2 a")).getText().shouldContain(productLine);
    }
}
