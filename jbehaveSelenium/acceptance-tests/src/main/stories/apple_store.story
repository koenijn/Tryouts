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
Then the total price will be 678.00 dollar
When I press on continue
Then I can personalize the iPad by engraving
When I choose to skip the engraving
Then the option to add accessories will be shown
When I add the order to the cart
Then the order overview will be shown
And the total price on the order overview will be 678.00 dollar
