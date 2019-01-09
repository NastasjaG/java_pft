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
      app.contact().create(new ContactData().withSecondname(app.getProperties().getProperty("valid.secondname"))
              .withCompanyname(app.getProperties().getProperty("valid.companyname"))
              .withPhone(app.getProperties().getProperty("valid.phone"))
              .withFirstname(app.getProperties().getProperty("valid.firstname"))
              .withGroup(app.getProperties().getProperty("valid.group")), true);
    }
  }

  @Test(enabled = true)

  public void testContactModification() {

    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();

    ContactData contactData = new ContactData()
            .withId(modifiedContact.getId())
            .withSecondname(app.getProperties().getProperty("validModif.secondname"))
            .withCompanyname(app.getProperties().getProperty("validModif.companyname"))
            .withPhone(app.getProperties().getProperty("validModif.phone"))
            .withFirstname(app.getProperties().getProperty("validModif.firstname"));

    app.contact().modify(contactData);

    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactData)));

  }


}
