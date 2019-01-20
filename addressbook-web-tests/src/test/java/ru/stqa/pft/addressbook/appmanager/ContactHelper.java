package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToContactPage() {
    click(By.linkText("home"));
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    if (contactData.getFirstname() != null) {
      type(By.name("firstname"), contactData.getFirstname());
    }
    if (contactData.getSecondname() != null) {
      type(By.name("lastname"), contactData.getSecondname());
    }
    if (contactData.getCompanyname() != null) {
      type(By.name("company"), contactData.getCompanyname());
    }
    if (contactData.getPhone() != null) {
      type(By.name("home"), contactData.getPhone());
    }
    if (contactData.getMobilePhone() != null) {
      type(By.name("mobile"), contactData.getMobilePhone());
    }
    if (contactData.getWorkPhone() != null) {
      type(By.name("work"), contactData.getWorkPhone());
    }
    if (contactData.getAddress() != null) {
      type(By.name("address"), contactData.getAddress());
    }
    if (contactData.getEmail1() != null) {
      type(By.name("email"), contactData.getEmail1());
    }
    if (contactData.getEmail2() != null) {
      type(By.name("email2"), contactData.getEmail2());
    }
    if (contactData.getEmail3() != null) {
      type(By.name("email3"), contactData.getEmail3());
    }
    attach(By.name("photo"), contactData.getPhoto());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void fillContactFormWithoutGroup(ContactData contactData) {
    if (contactData.getFirstname() != null) {
      type(By.name("firstname"), contactData.getFirstname());
    }
    if (contactData.getSecondname() != null) {
      type(By.name("lastname"), contactData.getSecondname());
    }
    if (contactData.getCompanyname() != null) {
      type(By.name("company"), contactData.getCompanyname());
    }
    if (contactData.getPhone() != null) {
      type(By.name("home"), contactData.getPhone());
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

  public void filterContactsNotInGroup() {
    wd.findElement(By.xpath("//select[@name='group']/option[@value='[none]']")).click();
  }

  public String filterContactsInGroup() {
    WebElement element = wd.findElement(By.xpath("//select[@name='group']/option[text() !='[none]' and text() !='[all]']"));
    String groupName = element.getText();
    element.click();
    return groupName;
  }

  public void selectContactNotInGroup(ContactData contactData) {
    wd.findElement(By.xpath(String.format("//input[@type='checkbox' and @value = '%s']", contactData.getId()))).click();
  }


  public void pushButtonAddToGroup() {
    wd.findElement(By.xpath("//input[@name='add']")).click();
  }

  public void pushButtonRemoveFromGroup() {
    wd.findElement(By.xpath("//input[@name='remove']")).click();
  }

  public void getGroupData(GroupData groupData){
    WebElement element = wd.findElement(By.xpath(String.format("//select[@name='group']/option[text() = '%s']", groupData.getName())));
    element.click();
  }



  public void initContactModification(int id) {
    // wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();

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

  public void createContactWithoutGroup(ContactData contactData) {
    initContactCreation();
    fillContactFormWithoutGroup(contactData);
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

  public void addContactToGroup(ContactData contact) {
    selectContactById(contact.getId())
    ;
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
      String allPhones = cells.get(5).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      int id = Integer.parseInt(element.findElement(By.xpath("./td[1]/input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withSecondname(secondname)
              .withAddress(address).withAllEmails(allEmails)
              .withAllPhones(allPhones));

    }
    return contacts;
  }

  public ContactData contactInfoFromEditForm(ContactData contactData) {
    selectContactById(contactData.getId());
    initContactModification(contactData.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");

    wd.navigate().back();
    return new ContactData().withId(contactData.getId()).withFirstname(firstname)
            .withSecondname(lastname).withHomePhone(home)
            .withAddress(address).withEmail1(email1).withEmail2(email2).withEmail3(email3)
            .withMobilePhone(mobile).withWorkPhone(work);
  }


}

