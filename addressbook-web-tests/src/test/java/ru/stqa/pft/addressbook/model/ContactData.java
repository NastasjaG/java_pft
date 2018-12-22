package ru.stqa.pft.addressbook.model;

public class ContactData {
  private int id = Integer.MAX_VALUE;
  private String secondname;
  private String companyname;
  private String phone;
  private String firstname;
  private String group;


  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withSecondname(String secondname) {
    this.secondname = secondname;
    return this;
  }

  public ContactData withCompanyname(String companyname) {
    this.companyname = companyname;
    return this;
  }

  public ContactData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public int getId() {
    return id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    return firstname != null ? firstname.equals(that.firstname) : that.firstname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            '}';

  }
}
