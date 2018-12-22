package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {
  @Test(enabled = true)
  public void testContactCreation() throws Exception {
    app.goTo().HomePage();
    Contacts before = app.contact().all();
    ContactData contactData = new ContactData().withSecondname("Petrova").withCompanyname("Company").withPhone("+79112345678").withFirstname("Julia").withGroup("test1");
    app.contact().create(contactData, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contactData.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    app.logout();
  }
}
