package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.interactions.SourceType;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {
  @Test(enabled = true)
  public void testContactCreation() throws Exception {
    app.goTo().HomePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/cat1.jpg");
    ContactData contactData = new ContactData().withSecondname("Petrova").withCompanyname("Company")
            .withPhoto(photo)
            .withPhone("+79112345678").withFirstname("Julia").withGroup("test1");
    app.contact().create(contactData, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contactData.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    app.logout();
  }

  /*
  @Test
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/cat1.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
    }*/
}
