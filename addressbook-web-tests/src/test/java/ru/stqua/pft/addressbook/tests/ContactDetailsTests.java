package ru.stqua.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alexander Gorny on 2/21/2017.
 */
public class ContactDetailsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData()
                      .withFirsName("Alexander")
                      .withLastName("Gorny")
                      .withNickname("Cool Woker")
                      .withTitle("Mr")
                      .withCompany("General Electric")
                      .withAddress("New Orleans")
                      .withHomePhone("")
                      .withMobilePhone("444-555-6666")
                      .withWorkPhone("")
                      .withEmail("cool@mail.com")
                      .withEmail2("woker@mail.com")
                      .withEmail3("")
                      .withUrlHomePage("www.homepage.com"),
              true);
      app.contact().gotoHomePage();
    }
  }

  @Test
  public void testContactDetails(){
    app.goTo().homePage();
    ContactData contact = app.db().contacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    String contactInfoDetailsPage = app.contact().contactInfoFromDetailsPage(contact);

    //contact data form db
    assertThat(cleaned(contactInfoDetailsPage), equalTo(cleaned(mergeAllContactData(contact).toString())));

    assertThat(cleaned(contactInfoDetailsPage), equalTo(cleaned(mergeAllContactData(contactInfoFromEditForm).toString())));
  }

  private String mergeAllContactData(ContactData contact) {
    return Arrays.asList(contact.getFirsName(),
            contact.getLastName(),
            contact.getNickname(),
            contact.getTitle(),
            contact.getCompany(),
            contact.getAddress(),
            contact.getHomePhone(),
            contact.getMobilePhone(),
            contact.getWorkPhone(),
            contact.getEmail(),
            contact.getEmail2(),
            contact.getEmail3(),
            contact.getUrlHomePage())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactDetailsTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String contact){
    return contact.replaceAll("\\s","").replaceAll("[-()]","").replaceAll("(H:|M:|W:)","").replaceAll("Homepage:","");
  }
}
