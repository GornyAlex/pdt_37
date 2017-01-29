package ru.stqua.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqua.pft.addressbook.model.ContactData;

/**
 * Created by Alexander Gorny on 1/29/2017.
 */
public class ContactHelper extends HelperBase{


  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {

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

  }

  public void initAddNewContact() {
    click(By.linkText("add new"));
  }

  public void selectContactFromTheList(int contactNumber) {
    click(By.xpath("(//input[@name='selected[]'])[" + contactNumber + "]"));
  }

  public void deleteSelectedContant() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }


  public void editContactFromTheList(int contactNumber) {
    click(By.xpath("(//a/img[@title='Edit'])[" + contactNumber + "]"));
  }

  public void updateContact() {
      click(By.name("update"));
  }
}
