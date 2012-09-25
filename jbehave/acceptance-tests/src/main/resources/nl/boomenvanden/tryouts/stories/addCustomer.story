
Narrative:
In order to file a new customer
As a office clerk
I want to create a new customer record in the application


Scenario: Incomplete customer

Given there is an unsaved customer
And the customer's companyName is empty
And the customer has no contactAddress
When the customer is saved
Then 2 validation errors occurs
And validation fails on companyName
And validation fails on contactAddress


Scenario: Incomplete contactAddress of customer

Given there is an unsaved customer
And the customer's companyName is Pietersen B.V.
And the customer has an empty contactAddress
When the customer is saved
Then 5 validation errors occurs
And validation fails on contactAddress.country
And validation fails on contactAddress.postalCode
And validation fails on contactAddress.city
And validation fails on contactAddress.street
And validation fails on contactAddress.houseNumber


Scenario: ContactAddress only missing country

Given there is an unsaved customer
And the customer's companyName is Pietersen B.V.
And the customer has a contactAddress with values
country: 
postalCode: 6466XS
city: Kerkrade
street: Petersstraat
houseNumber: 34
When the customer is saved
Then 1 validation error occurs
And validation fails on contactAddress.country


Scenario: Customer is saved

Given there is an unsaved customer
And the customer's companyName is Pietersen B.V.
And the customer has a contactAddress with values
country: The Netherlands
postalCode: 6466XS
city: Kerkrade
street: Petersstraat
houseNumber: 34
When the customer is saved
Then the customer is saved
And the saved customer's companyName is Pietersen B.V.
And the saved customer has a contactAddress with values
country: The Netherlands
postalCode: 6466XS
city: Kerkrade
street: Petersstraat
houseNumber: 34
