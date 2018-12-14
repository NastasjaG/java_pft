package ru.stqa.pft.addressbook.model;

public class ContactData {
  private  int id;
  private final String secondname;
  private final String companyname;
  private final String phone;
  private final String firstname;
  private String group;

  public ContactData(String secondname, String companyname, String phone, String firstname, String group) {
    this.id = Integer.MAX_VALUE;
    this.secondname = secondname;
    this.companyname = companyname;
    this.phone = phone;
    this.firstname = firstname;
    this.group = group;
  }
  public ContactData(int id,String secondname, String companyname, String phone, String firstname, String group) {
    this.id=id;
    this.secondname = secondname;
    this.companyname = companyname;
    this.phone = phone;
    this.firstname = firstname;
    this.group = group;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            '}';
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
}
