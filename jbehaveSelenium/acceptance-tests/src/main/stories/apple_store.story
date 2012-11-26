Browse the apple store

Meta:
@category advanced
@color red

Narrative: 

In order to show the browsing store functionality
As a user
I want to browse in the store

Scenario: Buying a white iPad Retina

Given I am on apple.com
When I navigate to the store section
Then the store home page will be shown
When I click on the iPad on the product shelf
Then the iPad store page will be shown
When I choose the iPad with retina display on the shelf
Then the iPad Retina configuration page will be shown
When I choose for the WHITE iPad
And the 32GB Wifi + Cellular version
And for the AT&T network
Then the summary contains product "iPad with Retina display Wi-Fi + Cellular for AT&T 32GB - White"
And the summary shows a price of $729.00
When I press on continue in the summary
Then the "iPad Engraving" page is shown
When I choose to skip the engraving
Then the "Accessories" page is shown
When I add the order to the cart
Then the "Cart" page is shown
And the order overview contains product "iPad with Retina display Wi-Fi + Cellular for AT&T 32GB - White"
And the total price on the order overview is $729.00
