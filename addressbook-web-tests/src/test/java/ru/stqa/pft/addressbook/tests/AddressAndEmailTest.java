package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Arrays;
import java.util.stream.Collectors;

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
      if (contactData.getEmail1() == null||contactData.getEmail1().isEmpty()) {
        contactData.withEmail1("PetrovaJ1@mail.ru");
      }
      if (contactData.getEmail2() == null||contactData.getEmail2().isEmpty()) {
        contactData.withEmail2("PetrovaJ2@mail.ru");
      }
      if (contactData.getEmail3() == null||contactData.getEmail3().isEmpty()) {
        contactData.withEmail3("PetrovaJ3@mail.ru");
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
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

  }

    private String address(ContactData contact) {
      return contact.getAddress();
    }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail1(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s) ->!s.equals(""))
            .collect(Collectors.joining("\n"));
  }
}