package ru.stqua.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.model.ContactData;

import java.util.Set;

/**
 * Created by Alexander Gorny on 1/29/2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData()
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
              .withGroup("test1"),
              true);
      app.contact().gotoHomePage();
    }
  }

  @Test
  public void testContactModification(){
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirsName("Chuck")
            .withLastName("Norris")
            .withNickname("Walker")
            .withTitle("Mr")
            .withCompany("Texas Ranger")
            .withAddress("Ryan")
            .withMobilePhone("123-321-1234")
            .withEmail1("cool@mail.com")
            .withEmail2("woker@mail.com")
            .withUrlHomePage("www.homepage.com");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();

    Assert.assertEquals(after.size(), before.size() );

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before,after);
  }


}
