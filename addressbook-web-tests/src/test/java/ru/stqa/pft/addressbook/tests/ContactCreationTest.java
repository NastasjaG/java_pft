package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Petrova", "Company", "+79112345678", "Julia","test1"),true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToContactPage();
    app.logout();
  }

}
