package ru.stqua.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test(enabled = false)
  public void testContactCreation() {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Alexander", "Gorny", "Cool Woker", "Mr", "GE", "New Orleans", "444-555-6666", "cool@mail.com", "woker@mail.com", "www.homepage.com", "test1");
    app.getContactHelper().createContact(contact, true);
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }


}
