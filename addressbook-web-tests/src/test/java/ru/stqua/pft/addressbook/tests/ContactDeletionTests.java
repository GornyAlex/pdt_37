package ru.stqua.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Alexander Gorny on 1/29/2017.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().selectContactFromTheList(1);
    app.getContactHelper().deleteSelectedContant();
    app.getNavigationHelper().returnToHomePage();

  }
}
