package nl.boomenvanden.tryouts.jbehaveSelenium.acceptance.steps;

import org.jbehave.core.annotations.Given;
import nl.boomenvanden.tryouts.jbehaveSelenium.acceptance.pages.Home;
import nl.boomenvanden.tryouts.jbehaveSelenium.acceptance.pages.PageFactory;
import nl.boomenvanden.tryouts.jbehaveSelenium.acceptance.pages.Site;
import nl.boomenvanden.tryouts.jbehaveSelenium.acceptance.pages.Store;
import nl.boomenvanden.tryouts.jbehaveSelenium.acceptance.pages.Store.IpadColor;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class AppleDotComSteps {

    private Site site;
    private Home home;
    private Store store;

    public AppleDotComSteps(PageFactory pageFactory) {
        site = pageFactory.newSite();
        home = pageFactory.newHome();
        store = pageFactory.newStore();
    }

    @Given("I am on apple.com")
    public void iAmOnTheHomePageOfAppleDotCom() {
        home.go();
    }

    @When("I navigate to the store section")
    public void whenINavigateToTheStoreSection() {
        home.openStore();
    }

    @Then("the store home page will be shown")
    public void thenTheStoreHomePageWillBeShown() {
        assertThat(site.getTitle(), containsString("Apple Store (U.S.)"));
    }

    @When("I click on the iPad on the product shelf")
    public void whenIClickOnTheIPadOnTheProductShelf() throws InterruptedException {
        store.shopForIPadThroughProductShelf();
    }

    @Then("the iPad store page will be shown")
    public void thenTheIPadStorePageWillBeShown() {
        assertThat(site.getCurrentUrl(), containsString("ipad"));
    }

    @When("I choose the iPad with retina display on the shelf")
    public void whenIChooseTheIPadWithRetinaDisplayOnTheShelf() {
        store.chooseIPadWithRetinaDisplay();
    }

    @Then("the iPad Retina configuration page will be shown")
    public void thenTheIPadRetinaConfigurationPageWillBeShown() {
        assertThat(site.getTitle(), containsString("iPad with Retina display"));
    }

    @When("I choose for the $color iPad")
    public void whenIChooseForTheWhiteIPad(String color) {
        IpadColor ipadColor = IpadColor.valueOf(color);
        store.chooseStepOneOption(ipadColor);
    }

    @When("the 32GB Wifi + Cellular version")
    public void whenThe32GBWifiCellularVersion() {
        store.chooseStepTwoOption();
    }

    @When("for the AT&T network")
    public void whenForTheATTNetwork() throws InterruptedException {
        Thread.sleep(5000);// temporary needed because of waiting for element to be visible
        store.chooseStepThreeOption();
    }

    @Then("the total price will be 678.00 dollar")
    @Pending
    public void thenTheTotalPriceWillBe67800Dollar() {
        // PENDING
    }

    @When("I press on continue")
    @Pending
    public void whenIPressOnContinue() {
        // PENDING
    }

    @Then("I can personalize the iPad by engraving")
    @Pending
    public void thenICanPersonalizeTheIPadByEngraving() {
        // PENDING
    }

    @When("I choose to skip the engraving")
    @Pending
    public void whenIChooseToSkipTheEngraving() {
        // PENDING
    }

    @Then("the option to add accessories will be shown")
    @Pending
    public void thenTheOptionToAddAccessoriesWillBeShown() {
        // PENDING
    }

    @When("I add the order to the cart")
    @Pending
    public void whenIAddTheOrderToTheCart() {
        // PENDING
    }

    @Then("the order overview will be shown")
    @Pending
    public void thenTheOrderOverviewWillBeShown() {
        // PENDING
    }

    @Then("the total price on the order overview will be 678.00 dollar")
    @Pending
    public void thenTheTotalPriceOnTheOrderOverviewWillBe67800Dollar() {
        // PENDING
    }
}
