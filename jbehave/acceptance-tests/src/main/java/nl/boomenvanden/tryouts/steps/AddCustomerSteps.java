package nl.boomenvanden.tryouts.steps;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import nl.boomenvanden.tryouts.jbehave.application.CustomerRepository;
import nl.boomenvanden.tryouts.jbehave.application.CustomerService;
import nl.boomenvanden.tryouts.jbehave.application.domain.Address;
import nl.boomenvanden.tryouts.jbehave.application.domain.Customer;
import nl.boomenvanden.tryouts.jbehave.application.infrastructure.JpaCustomerRepository;
import org.jbehave.core.annotations.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AddCustomerSteps extends BaseJpaTestCase {

    Customer unsavedCustomer;
    Address contactAddress;
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

    @When("the customer's companyName is empty")
    public void givenTheCustomersCompanyNameIsEmpty() {
        unsavedCustomer.setCompanyName(null);
    }

    @When("the customer's companyName is $companyName")
    public void givenTheCustomersCompanyNameIs(String companyName) {
        unsavedCustomer.setCompanyName(companyName);
    }

    @When("the customer has no contactAddress")
    public void whenTheCustomerHasNoContactAddress() {
        unsavedCustomer.setContactAddress(null);
    }

    @When("the customer has an empty contactAddress")
    public void whenTheCustomerHasAContactAddress() {
        contactAddress = new Address();
        unsavedCustomer.setContactAddress(contactAddress);
    }

    @When("the customer has a contactAddress with values\ncountry: $country\npostalCode: $postalCode\ncity: $city\nstreet: $street\nhouseNumber: $houseNumber")
    public void whenTheCustomerHasAContactAddressWithValuesCountryPostalCodeCityStreetHouseNumber(
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

    @Then("a validation error occurs")
    public void thenValidationFails() {
        printException();
        assertTrue("An validation error should occur", exceptionThrown instanceof ConstraintViolationException);
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

        Customer savedCustomer = em.find(Customer.class, customerNumber);
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
