package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

  @BeforeMethod
  public void insurePreconditions() {
    app.goTo().HomePage();
    Contacts before = app.contact().all();
    for(ContactData contactData:before) {
      if (contactData.getMobilePhone() == null) {
        contactData.withMobilePhone("12345");
      }
      if (contactData.getWorkPhone() == null) {
        contactData.withWorkPhone("67890");
      }
      app.contact().modify(contactData);

    }
  }

  @Test
  public void testContactPhones(){
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);


    assertThat(contact.getPhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));

  }
public String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
}
}
