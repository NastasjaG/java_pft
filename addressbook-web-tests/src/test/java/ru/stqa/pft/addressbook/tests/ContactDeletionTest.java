package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTest extends TestBase {


  @BeforeMethod
  public  void insurePreconditions(){
    app.goTo().HomePage();
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData().withSecondname("Petrova").withCompanyname("Company").withPhone("+79112345678").withFirstname("Julia").withGroup("test1"), true);
    }
  }

  @Test(enabled = true)

  public void testContactDeletion() throws Exception {

    Set<ContactData> before=app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().HomePage();
    Set<ContactData> after=app.contact().all();
    Assert.assertEquals(after.size(), before.size()-1 );

    before.remove(deletedContact);
      Assert.assertEquals(before, after);

  }


  }

