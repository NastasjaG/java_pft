package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public  void insurePreconditions(){
    app.goTo().HomePage();
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData().withSecondname("Petrova").withCompanyname("Company").withPhone("+79112345678").withFirstname("Julia").withGroup("test1"), true);
    }
  }

  @Test(enabled = true)

  public void testContactModification() {

    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();

    ContactData contactData = new ContactData()
            .withId(modifiedContact.getId()).withSecondname("Petrova").withCompanyname("Company").withPhone("+79112345678").withFirstname("Julia");

    app.contact().modify(contactData);

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contactData);


    Assert.assertEquals(before, after);

  }


}
