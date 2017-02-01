package ru.stqua.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.model.ContactData;

/**
 * Created by Alexander Gorny on 1/29/2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().editContactFromTheList(1);
    app.getContactHelper().fillContactForm(new ContactData("Chuck", "Norris", "Walker", "Mr", "Texas Ranger", "Ryan", "444-555-6666", "cool@mail.com", "woker@mail.com", "www.homepage.com", null), false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().gotoHomePage();

  }

}
