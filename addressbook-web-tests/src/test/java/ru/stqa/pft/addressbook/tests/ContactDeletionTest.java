package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTest extends TestBase {


  @BeforeMethod
  public void insurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withSecondname(app.getProperties().getProperty("valid.secondname"))
              .withCompanyname(app.getProperties().getProperty("valid.companyname"))
              .withPhone(app.getProperties().getProperty("valid.phone"))
              .withFirstname(app.getProperties().getProperty("valid.firstname"))
              .withGroup(app.getProperties().getProperty("valid.group")), true);
    }
  }

  @Test(enabled = true)

  public void testContactDeletion() throws Exception {

    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().HomePage();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));

  }


}

