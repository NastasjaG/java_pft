package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String secondname;
  private final String companyname;
  private final String phone;
  private final String firstname;
  private String group;

  public ContactData(String secondname, String companyname, String phone, String firstname, String group) {
    this.secondname = secondname;
    this.companyname = companyname;
    this.phone = phone;
    this.firstname = firstname;
    this.group = group;
  }

  public String getSecondname() {
    return secondname;
  }

  public String getCompanyname() {
    return companyname;
  }

  public String getPhone() {
    return phone;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getGroup() {
    return group;
  }
}
