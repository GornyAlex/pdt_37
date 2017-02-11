package ru.stqua.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqua.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Gorny on 1/29/2017.
 */
public class ContactHelper extends HelperBase{


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {

    type(By.name("firstname"),contactData.getFirsName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"),contactData.getAddress());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail1());
    type(By.name("title"), contactData.getTitle());
    type(By.name("email2"), contactData.getEmail2());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initAddNewContact() {
    click(By.linkText("add new"));
  }

  public void selectContactFromTheList(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContant() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }


  public void editContactFromTheList(int index) {
    wd.findElements(By.xpath("//a/img[@title='Edit']")).get(index).click();
  }

  public void updateContact() {
      click(By.name("update"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("(//input[@name='selected[]'])[1]"));
  }

  public void createContact(ContactData contact, boolean creation) {
    initAddNewContact();
    fillContactForm(contact, creation);
    submitContactCreation();
  }

  public void modifyContact(ContactData contact, int contactNumber) {
    editContactFromTheList(contactNumber);
    fillContactForm(contact, false);
    updateContact();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements){
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastName = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      ContactData contact = new ContactData(id, firstName, lastName, null, null, null, null, null, null, null, null, null);
      contacts.add(contact);
  }
    return contacts;
  }
}
