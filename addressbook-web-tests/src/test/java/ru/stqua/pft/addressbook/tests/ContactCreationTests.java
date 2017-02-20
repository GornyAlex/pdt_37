package ru.stqua.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.model.ContactData;
import ru.stqua.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
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
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
