package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class DeleteContactFromGroup extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    app.goTo().HomePage();
    String groupName = app.contact().filterContactsInGroup();
    if (app.contact().all().size()== 0) {
     app.contact().create(new ContactData().withSecondname(app.getProperties().getProperty("valid.secondname"))
             .withCompanyname(app.getProperties().getProperty("valid.companyname"))
             .withPhone(app.getProperties().getProperty("valid.phone"))
             .withFirstname(app.getProperties().getProperty("valid.firstname"))
             .withGroup(groupName), true);
   }
  }

    @Test
  public void addContactToGroup(){
    app.goTo().HomePage();
      ContactData contactData = app.db().contactInGroup();
      GroupData groupData = contactData.getGroups().iterator().next();
      app.contact().getGroupData(groupData);
      app.contact().pushButtonRemoveFromGroup();
      app.goTo().HomePage();
    }
}
