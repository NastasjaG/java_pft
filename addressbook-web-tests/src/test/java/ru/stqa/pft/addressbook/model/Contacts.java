package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

  private Set<ContactData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ContactData>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<ContactData>();
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactData contactdata) {
    Contacts contacts = new Contacts(this);
    contacts.add(contactdata);
    return contacts;
  }

  public Contacts without(ContactData contactdata) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contactdata);
    return contacts;
  }
}
