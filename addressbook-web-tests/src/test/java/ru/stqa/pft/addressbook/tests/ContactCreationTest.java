package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTest extends TestBase {
  @Test(enabled = true)
  public void testContactCreation() throws Exception {
    app.goTo().HomePage();
    Set<ContactData> before=app.contact().all();
    ContactData contactData = new ContactData().withSecondname("Petrova").withCompanyname("Company").withPhone("+79112345678").withFirstname("Julia").withGroup("test1");
    app.contact().create(contactData, true);
    Set<ContactData> after=app.contact().all();
    Assert.assertEquals(after.size(), before.size() +1);

    contactData.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    before.add(contactData);

    Assert.assertEquals(before, after);
    app.logout();
  }
}
