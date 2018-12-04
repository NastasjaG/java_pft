package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().createContact(new ContactData("Petrova", "Company", "+79112345678", "Julia", "test1"), true);
    app.logout();
  }

}
