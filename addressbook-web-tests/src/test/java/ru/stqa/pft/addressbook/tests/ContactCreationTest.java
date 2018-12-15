package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
public class ContactCreationTest extends TestBase {
  @Test(enabled = false)
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before=app.getContactHelper().getContactList();
    ContactData contactData = new ContactData("Petrova", "Company", "+79112345678", "Julia","test1");
    app.getContactHelper().createContact(contactData, true);
    List<ContactData> after=app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() +1);

    before.add(contactData);
    Comparator<? super ContactData> byId= (c1, c2) ->Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    app.logout();
  }
}
