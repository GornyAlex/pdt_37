package ru.stqua.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.getContactHelper().initAddNewContact();
    app.getContactHelper().fillContactForm(new ContactData("Alex", "Gorny", "Cool Woker", "Mr", "GE", "New Orleans", "444-555-6666", "cool@mail.com", "woker@mail.com", "www.homepage.com"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
  }


}
