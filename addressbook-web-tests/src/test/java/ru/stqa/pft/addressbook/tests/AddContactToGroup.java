package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertTrue;

public class AddContactToGroup extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    app.goTo().HomePage();
    app.contact().filterContactsNotInGroup();
    if (app.contact().all().size()== 0) {
      app.contact().createContactWithoutGroup(new ContactData().withSecondname(app.getProperties().getProperty("contactForGroup.secondname"))
              .withCompanyname(app.getProperties().getProperty("contactForGroup.companyname"))
              .withPhone(app.getProperties().getProperty("contactForGroup.phone"))
              .withFirstname(app.getProperties().getProperty("contactForGroup.firstname")));
    }
  }

    @Test
  public void addContactToGroup(){
    app.goTo().HomePage();
 
      ContactData contactData = app.db().contactNotInGroup();
      app.contact().selectContactNotInGroup(contactData);
      Groups groups = app.db().groups();
      GroupData group = groups.iterator().next();
      app.contact().selectGroup(group);
      app.contact().pushButtonAddToGroup();
      ContactData contactData1 = app.db().contactById(contactData.getId());
      assertTrue( contactData1.getGroups().contains(group));
  }
}
