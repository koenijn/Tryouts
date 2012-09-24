
Narrative:
In order to file a new customer
As a office clerk
I want to create a new customer record in the application


Scenario: Incomplete customer

Given there is an unsaved customer
When the customer's companyName is empty
And the customer has no contactAddress
And the customer is saved
Then a validation error occurs
And validation fails on companyName
And validation fails on contactAddress

Scenario: Incomplete contactAddress of customer

Given there is an unsaved customer
When the customer's companyName is Pietersen B.V.
And the customer has an empty contactAddress
And the customer is saved
Then a validation error occurs
And validation fails on contactAddress.country
And validation fails on contactAddress.postalCode
And validation fails on contactAddress.city
And validation fails on contactAddress.street
And validation fails on contactAddress.houseNumber

Scenario: Customer is saved

Given there is an unsaved customer
When the customer's companyName is Pietersen B.V.
And the customer has a contactAddress with values
country: The Netherlands
postalCode: 6466XS
city: Kerkrade
street: Petersstraat
houseNumber: 34
And the customer is saved
Then the customer is saved

