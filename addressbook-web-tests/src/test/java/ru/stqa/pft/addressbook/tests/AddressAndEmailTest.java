package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressAndEmailTest extends TestBase{

  @BeforeMethod
  public void insurePreconditions() {
    app.goTo().HomePage();
    Contacts before = app.contact().all();
    for(ContactData contactData:before) {
      if (contactData.getAddress() == null||contactData.getAddress().isEmpty()) {
        contactData.withAddress("Prospect Mira 9/4 kv85");
      }
      if (contactData.getEmail() == null||contactData.getEmail().isEmpty()) {
        contactData.withEmail("PetrovaJ@mail.ru");
      }
      app.contact().modify(contactData);

    }
  }

  @Test
  public void testAddressAndEmail() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);


    assertThat(contact.getAddress(), equalTo(address(contactInfoFromEditForm)));
    assertThat(contact.getEmail(), equalTo(email(contactInfoFromEditForm)));
  }

    private String address(ContactData contact) {
      return contact.getAddress();
  }
  private String email(ContactData contact) {
    return contact.getEmail();
  }
}
