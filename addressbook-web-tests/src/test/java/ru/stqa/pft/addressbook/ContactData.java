package ru.stqa.pft.addressbook;

public class ContactData {
  private final String secondname;
  private final String companyname;
  private final String phone;
  private final String firstname;

  public ContactData(String secondname, String companyname, String phone, String firstname) {
    this.secondname = secondname;
    this.companyname = companyname;
    this.phone = phone;
    this.firstname = firstname;
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
}
