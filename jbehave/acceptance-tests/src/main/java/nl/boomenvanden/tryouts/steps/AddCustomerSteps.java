package nl.boomenvanden.tryouts.steps;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import nl.boomenvanden.tryouts.jbehave.application.CustomerRepository;
import nl.boomenvanden.tryouts.jbehave.application.CustomerService;
import nl.boomenvanden.tryouts.jbehave.application.domain.Address;
import nl.boomenvanden.tryouts.jbehave.application.domain.Customer;
import nl.boomenvanden.tryouts.jbehave.application.infrastructure.JpaCustomerRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.jbehave.core.annotations.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AddCustomerSteps extends BaseJpaTestCase {

    Customer unsavedCustomer;
    Address contactAddress;
    
    Customer savedCustomer;
    
    CustomerService customerService;
    CustomerRepository customerRepository;
    Exception exceptionThrown;

    @BeforeScenario
    public void beforeScenario() throws Exception {
        super.beforeScenario();
        customerRepository = new JpaCustomerRepository(em);
        customerService = new CustomerService(customerRepository);

        exceptionThrown = null;
    }

    @AfterScenario
    public void afterScenario() throws Exception {
        super.afterScenario();
    }

    @Given("there is an unsaved customer")
    public void givenThereIsAnUnsavedCustomer() {
        unsavedCustomer = new Customer();
    }

    @Given("the customer's companyName is empty")
    public void givenTheCustomersCompanyNameIsEmpty() {
        unsavedCustomer.setCompanyName(null);
    }

    @Given("the customer's companyName is $companyName")
    public void givenTheCustomersCompanyNameIs(String companyName) {
        unsavedCustomer.setCompanyName(companyName);
    }

    @Given("the customer has no contactAddress")
    public void givenTheCustomerHasNoContactAddress() {
        unsavedCustomer.setContactAddress(null);
    }

    @Given("the customer has an empty contactAddress")
    public void givenTheCustomerHasAContactAddress() {
        contactAddress = new Address();
        unsavedCustomer.setContactAddress(contactAddress);
    }

    @Given("the customer has a contactAddress with values\ncountry: $country\npostalCode: $postalCode\ncity: $city\nstreet: $street\nhouseNumber: $houseNumber")
    public void givenTheCustomerHasAContactAddressWithValuesCountryPostalCodeCityStreetHouseNumber(
            String country,
            String postalCode,
            String city,
            String street,
            String houseNumber) {
        contactAddress = new Address();
        contactAddress.setCountry(country);
        contactAddress.setPostalCode(postalCode);
        contactAddress.setCity(city);
        contactAddress.setStreet(street);
        contactAddress.setHouseNumber(houseNumber);
        
        unsavedCustomer.setContactAddress(contactAddress);
    }

    @When("the customer is saved")
    public void whenTheCustomerIsSaved() {
        try {
            em.getTransaction().begin();
            customerService.createNewCustomer(unsavedCustomer);
            em.getTransaction().commit();
        } catch (Exception e) {
            exceptionThrown = e;
        }
    }
    
    @Then("the saved customer's companyName is $companyName")
    public void thenTheCustomersCompanyNameIs(String companyName) {
        assertEquals(companyName, savedCustomer.getCompanyName());
    }
    
    @Then("the saved customer has a contactAddress with values\ncountry: $country\npostalCode: $postalCode\ncity: $city\nstreet: $street\nhouseNumber: $houseNumber")
    public void thenTheCustomerHasAContactAddressWithValuesCountryPostalCodeCityStreetHouseNumber(
            String country,
            String postalCode,
            String city,
            String street,
            String houseNumber) {
        contactAddress = savedCustomer.getContactAddress();
        
        assertEquals(country, contactAddress.getCountry());
        assertEquals(postalCode, contactAddress.getPostalCode());
        assertEquals(city, contactAddress.getCity());
        assertEquals(street, contactAddress.getStreet());
        assertEquals(houseNumber, contactAddress.getHouseNumber());
    }



    @Then("$count validation error{s|} occurs")
    public void thenValidationFails(int count) {
        printException();
        assertTrue("An validation error should occur", exceptionThrown instanceof ConstraintViolationException);
        assertEquals(count, ((ConstraintViolationException) exceptionThrown).getConstraintViolations().size());
    }

    @Then("validation fails on $fieldName")
    public void thenValidationFailsOn(String fieldName) {
        ConstraintViolationException e = (ConstraintViolationException) exceptionThrown;
        boolean found = false;

        for (ConstraintViolation violation : e.getConstraintViolations()) {
            String foundFieldName = violation.getPropertyPath().toString();
            
            if (foundFieldName.equals(fieldName)) {
                found = true;
                break;
            }
        }

        assertTrue("Should find validation message for field " + fieldName, found);
    }

    @Then("the customer is saved")
    public void thenTheCustomerIsSaved() {
        printException();
        assertNull(exceptionThrown);

        Long customerNumber = unsavedCustomer.getCustomerNumber();
        assertNotNull(unsavedCustomer.getCustomerNumber());

        this.savedCustomer = em.find(Customer.class, customerNumber);
        assertNotNull(savedCustomer);
    }

    private void printException() {
        if (exceptionThrown != null) {
            if (exceptionThrown instanceof ConstraintViolationException) {
                ConstraintViolationException e = (ConstraintViolationException) exceptionThrown;

                for (ConstraintViolation violation : e.getConstraintViolations()) {
                    String foundFieldName = violation.getPropertyPath().toString();
                    System.out.println(foundFieldName + ": " + violation.getMessage());
                }
            } else {
                exceptionThrown.printStackTrace();
            }
        }
    }
}
