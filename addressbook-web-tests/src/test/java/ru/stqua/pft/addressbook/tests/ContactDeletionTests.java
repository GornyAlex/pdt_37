package ru.stqua.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Alexander Gorny on 1/29/2017.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().list().size() == 0){
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
  public void testContactDeletion() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);


  }

}
