package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.getGroupHelper().selectContact();
    app.getGroupHelper().deleteSelectedContact();
    app.getGroupHelper().acceptDeletionAlert();

  }

}
