package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withSecondname("Petrova").withCompanyname("Company").withPhone("+79112345678").withFirstname("Julia").withGroup("test1"), true);
    }
  }

  @Test(enabled = true)

  public void testContactModification() {

    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();

    ContactData contactData = new ContactData()
            .withId(modifiedContact.getId()).withSecondname("Petrova_mod").withCompanyname("Company").withPhone("+79112345678").withFirstname("Julia_mod");

    app.contact().modify(contactData);

    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactData)));

  }


}
