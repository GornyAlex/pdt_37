package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Alexander Gorny on 3/19/2017.
 */
public class PasswordChangeTests extends TestBase{

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }


  @Test
  public void testUserPasswordChange() throws IOException, MessagingException {
    //login as admin, select a user, and click Reset Password button
    app.admin().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.admin().selectUser();
    User user = app.admin().userFromEditFom();
    app.admin().resetUserPassword();

    //wai for an email
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);

    //generate a new password
    long now = System.currentTimeMillis();
    String newPassword = "new_password" + now;

   //change password using information from email
    String confirmationLink = findConfirmationLink(mailMessages, User.getEmail());
    app.registration().finish(confirmationLink, newPassword);

    //verify that user can login with the new password
    assertTrue(app.newSession().login(User.getUsername(), newPassword));
  }


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
