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
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getSecondname());
    type(By.name("company"), contactData.getCompanyname());
    type(By.name("mobile"), contactData.getPhone());

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
      String firstname = element.findElement(By.xpath("./td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.xpath("./td[1]/input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname));
    }
    return contacts;
  }


}

