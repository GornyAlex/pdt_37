package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by Alexander Gorny on 3/18/2017.
 */
public class RegistrationTests extends TestBase{

  @Test
  public void testRegistration(){
    app.registration().start("user1", "usr1@localhost.localdomain");
  }
}
