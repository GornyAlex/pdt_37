package ru.stqua.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirsName("Alexander")
            .withLastName("Gorny")
            .withNickname("Cool Woker")
            .withTitle("Mr")
            .withCompany("General Electric")
            .withAddress("New Orleans")
            .withMobilePhone("444-555-6666")
            .withEmail1("cool@mail.com")
            .withEmail2("woker@mail.com")
            .withUrlHomePage("www.homepage.com")
            .withGroup("test1");
    app.contact().create(contact, true);
    app.contact().gotoHomePage();
    Set<ContactData> after = app.contact().all();

    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before,after);
  }


}
