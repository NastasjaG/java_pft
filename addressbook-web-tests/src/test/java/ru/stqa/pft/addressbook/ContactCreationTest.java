package ru.stqa.pft.addressbook;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactForm(new ContactData("Petrova", "Company", "+79112345678", "Julia"));
    submitContactCreation();
    returnToContactPage();
    logout();
  }

}
