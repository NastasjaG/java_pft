package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {


  @BeforeMethod
  public  void insurePreconditions(){
    app.goTo().HomePage();
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData("Petrova", "Company", "+79112345678", "Julia", "test1"), true);
    }
  }

  @Test(enabled = true)

  public void testContactDeletion() throws Exception {

    List<ContactData> before=app.contact().list();
    int index = before.size()-1;
    app.contact().delete(index);
    app.goTo().HomePage();
    List<ContactData> after=app.contact().list();
    Assert.assertEquals(after.size(), before.size()-1 );

    before.remove(index);
      Assert.assertEquals(before, after);

  }


  }

