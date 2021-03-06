package ru.stqua.pft.addressbook.tests;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.model.ContactData;
import ru.stqua.pft.addressbook.model.Contacts;
import ru.stqua.pft.addressbook.model.GroupData;
import ru.stqua.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Alexander Gorny on 3/12/2017.
 */
public class ContactDelitionFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    Contacts contactsList = app.db().contacts();
    Groups groupsList = app.db().groups();

    app.goTo().homePage();

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Group for add a contact test"));
    } else {
      //ensure precondition:
      //is there a contact that  belongs to any group?
      //otherwise add a contact to a group
      boolean groupFound = false;
      //I tried to do this with iterator and while loop, but was unable to fix infinitive loop
      for (ContactData contact : contactsList) {
        for (GroupData group : groupsList) {
          if (StringUtils.containsIgnoreCase(contact.getGroups().toString(), group.toString())) {
            groupFound = true;
            break;
          }
        }
      }
      if (!groupFound){
        ContactData selectedContact = app.db().contacts().iterator().next();
        GroupData selectedGroup = app.db().groups().iterator().next();
        app.contact().addToGroup(selectedContact, selectedGroup);
      }
    }

    groupsList = app.db().groups();
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
                      .withUrlHomePage("www.homepage.com")
                      .inGroup(groupsList.iterator().next()),
              true);
      app.contact().gotoHomePage();
    }
  }

  @Test
  public void testIfContactCouldBeDeletedFromGroup(){
    Contacts contactsList = app.db().contacts();
    Groups groupsList = app.db().groups();

    ContactData selectedContact = contactsList.iterator().next();
    GroupData selectedGroup = groupsList.iterator().next();

    //find a contact that not belongs to selected group
    //I tried to do this with iterator and while loop, but was unable to fix infinitive loop
    for (ContactData contact : contactsList) {
      for (GroupData group : groupsList) {
        if (StringUtils.containsIgnoreCase(contact.getGroups().toString(), group.toString())) {
          selectedContact = contact;
          selectedGroup = group;
          break;
        }
      }
    }

    app.contact().gotoHomePage();
    app.contact().deleteFromGroup(selectedContact, selectedGroup);

    assertThat(app.db().contactById(selectedContact.getId()).iterator().next().getGroups(), equalTo(selectedContact.getGroups().without(selectedGroup)));

  }

}
