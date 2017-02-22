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
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData()
                      .withFirsName("Alexander")
                      .withLastName("Gorny")
                      .withAddress("123 Some St.,\n" +
                              "New Orleans, LA\n" +
                              "70112")
                      .withHomePhone("+1-444-543-2345")
                      .withMobilePhone("(375) 29 6 196 537")
                      .withWorPhone("+1 555-345-876")
                      .withEmail("email@email.com")
                      .withEmail2("2email@2email.2com")
                      .withEmail3("3email@3email.3com"),
                     true);
      app.contact().gotoHomePage();
    }
  }

  @Test
  public void testContactDetails(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    String contactInfoDetailsPage = app.contact().contactInfoFromDetailsPage(contact);

    assertThat(cleaned(contactInfoDetailsPage), equalTo(cleaned(mergeAllContactDataFromEditForm(contactInfoFromEditForm).toString())));
  }

  private String mergeAllContactDataFromEditForm(ContactData contact) {
    return Arrays.asList(contact.getFirsName(),
            contact.getLastName(),
            contact.getAddress(),
            contact.getHomePhone(),
            contact.getMobilePhone(),
            contact.getWorkPhone(),
            contact.getEmail(),
            contact.getEmail2(),
            contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactDetailsTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String contact){
    return contact.replaceAll("\\s","").replaceAll("[-()]","").replaceAll("(H:|M:|W:)","");
  }
}
