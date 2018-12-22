package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
public class ContactCreationTest extends TestBase {
  @Test(enabled = true)
  public void testContactCreation() throws Exception {
    app.goTo().HomePage();
    List<ContactData> before=app.contact().list();
    ContactData contactData = new ContactData().withSecondname("Petrova").withCompanyname("Company").withPhone("+79112345678").withFirstname("Julia").withGroup("test1");
    app.contact().create(contactData, true);
    List<ContactData> after=app.contact().list();
    Assert.assertEquals(after.size(), before.size() +1);

    before.add(contactData);
    Comparator<? super ContactData> byId= (c1, c2) ->Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    app.logout();
  }
}
