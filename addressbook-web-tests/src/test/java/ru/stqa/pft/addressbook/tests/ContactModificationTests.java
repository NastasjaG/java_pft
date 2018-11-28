package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Petrova1", "Company", "+79112345678", "Julia"));
    app.getContactHelper().updateContactCreation();
    app.getContactHelper().returnToContactPage();
  }
}