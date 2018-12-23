package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToContactPage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    if (contactData.getFirstname()!=null)  {
      type(By.name("firstname"), contactData.getFirstname());
    }
    if (contactData.getSecondname()!=null) {
      type(By.name("lastname"), contactData.getSecondname());
    }
    if (contactData.getCompanyname()!=null) {
      type(By.name("company"), contactData.getCompanyname());
    }
    if (contactData.getPhone()!=null) {
      type(By.name("home"), contactData.getPhone());
    }
    if (contactData.getMobilePhone()!=null) {
      type(By.name("mobile"), contactData.getMobilePhone());
    }
    if (contactData.getWorkPhone()!=null) {
      type(By.name("work"), contactData.getWorkPhone());
    }

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void acceptDeletionAlert() {
    wd.switchTo().alert().accept();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
  }

  public void initContactModification(int id) {
    // wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    wd.findElement(By.cssSelector("input[value = '" + id + "']"))
            .findElement(By.xpath("../..")).findElement(By.xpath("//img[@alt='Edit']")).click();
  }

  public void updateContactCreation() {
    click(By.name("update"));
  }

  public void create(ContactData contactData, boolean b) {
    initContactCreation();
    fillContactForm(contactData, b);
    submitContactCreation();
    returnToContactPage();
  }

  public void modify(ContactData contactData) {
    selectContactById(contactData.getId());
    initContactModification(contactData.getId());
    fillContactForm(contactData, false);
    updateContactCreation();
    returnToContactPage();
  }


  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    acceptDeletionAlert();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//table//tr [position() != 1]"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstname = element.findElement(By.xpath("./td[3]")).getText();
      String secondname = cells.get(1).getText();
      String[] phones = cells.get(5).getText().split("\n");
      int id = Integer.parseInt(element.findElement(By.xpath("./td[1]/input")).getAttribute("value"));
      if (phones.length > 2) {
        contacts.add(new ContactData().withId(id).withFirstname(firstname).withSecondname(secondname)
                .withPhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
      }

    }
    return contacts;
  }

public ContactData contactInfoFromEditForm(ContactData contactData){
  selectContactById(contactData.getId());
  initContactModification(contactData.getId());
  String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
  String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
  String home = wd.findElement(By.name("home")).getAttribute("value");
  String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
  String work = wd.findElement(By.name("work")).getAttribute("value");
wd.navigate().back();
return new ContactData().withId(contactData.getId()).withFirstname(firstname)
        .withSecondname(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
}
}

