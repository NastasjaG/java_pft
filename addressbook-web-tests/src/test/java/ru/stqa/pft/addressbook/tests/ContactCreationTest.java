package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getGroupHelper().initContactCreation();
    app.getGroupHelper().fillContactForm(new ContactData("Petrova", "Company", "+79112345678", "Julia"));
    app.getGroupHelper().submitContactCreation();
    app.getGroupHelper().returnToContactPage();
    app.logout();
  }

}
