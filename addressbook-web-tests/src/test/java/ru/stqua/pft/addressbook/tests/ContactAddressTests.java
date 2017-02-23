package ru.stqua.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alexander Gorny on 2/20/2017.
 */
public class ContactAddressTests extends TestBase {

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
                      .withEmail("cool@mail.com")
                      .withEmail2("woker@mail.com")
                      .withUrlHomePage("www.homepage.com")
                      .withGroup("test1"),
              true);
      app.contact().gotoHomePage();
    }
  }

  @Test
  public void testContactAddress(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm =app.contact().infoFromEditForm(contact);

    assertThat(cleaned(contact.getAddress()), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
  }

  public static String cleaned(String address){
    return address.replaceAll("\\s","");
  }
}