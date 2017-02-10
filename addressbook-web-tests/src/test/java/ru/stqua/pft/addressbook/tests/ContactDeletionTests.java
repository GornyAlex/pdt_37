package ru.stqua.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Alexander Gorny on 1/29/2017.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Alex", "Gorny", "Cool Woker", "Mr", "GE", "New Orleans", "444-555-6666", "cool@mail.com", "woker@mail.com", "www.homepage.com", "test1"), true);
      app.getNavigationHelper().gotoHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContactFromTheList(before.size() - 1); //last contact
    app.getContactHelper().deleteSelectedContant();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);


  }
}
