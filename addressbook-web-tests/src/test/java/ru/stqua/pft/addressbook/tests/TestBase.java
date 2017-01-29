package ru.stqua.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqua.pft.addressbook.appmanager.ApplicationManager;
import ru.stqua.pft.addressbook.model.ContactData;

/**
 * Created by Alexander Gorny on 1/24/2017.
 */
public class TestBase {

  protected final ApplicationManager app = new ApplicationManager();


  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod
  public void tearDown() {
    app.stop();
  }


}
