package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public  void insurePreconditions(){
    app.goTo().HomePage();
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData("Petrova", "Company", "+79112345678", "Julia", "test1"), true);
    }
  }

  @Test(enabled = false)

  public void testContactModification() {

    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contactData = new ContactData(before.get(index).getId(), "Petrova1", "Company", "+79112345678", "Julia", null);

    app.contact().modify(index, contactData);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contactData);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);

  }


}
