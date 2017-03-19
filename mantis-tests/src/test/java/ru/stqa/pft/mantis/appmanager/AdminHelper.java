package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.stqa.pft.mantis.model.User;

import java.util.List;

/**
 * Created by Alexander Gorny on 3/19/2017.
 */
public class AdminHelper extends HelperBase {

  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void selectUser() {
    click(By.xpath("//button[@id='menu-toggler']"));
    click(By.xpath("//span[contains(.,'Manage')]"));
    click(By.xpath("//a[contains(.,'Manage Users')]"));
    List<WebElement> users = wd.findElements(By.xpath("//a[contains(@href,'manage_user_edit_page.php')]"));
    WebElement user = users.stream().filter((u) -> !(u.getText().equals(app.getProperty("web.adminLogin"))))
            .findAny().get();

//    for Firefox
//    ((JavascriptExecutor)wd).executeScript("window.scrollTo(0,"+user.getLocation().y+")");
//    Actions actions = new Actions(wd);
//    actions.moveToElement(user).perform();
//    click(By.xpath("//a[contains(.,'" + user.getText() + "')]"));

    user.click();

  }

  public User userFromEditFom() {
    String name = wd.findElement(By.name("username")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    return new User().withUsername(name).withEmail(email);
  }

  public void resetUserPassword() {
    click(By.xpath("//input[@value='Reset Password']"));
  }
}

