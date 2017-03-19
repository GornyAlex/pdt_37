package ru.stqa.pft.mantis.model;

/**
 * Created by Alexander Gorny on 3/19/2017.
 */
public class User {

  private static String username;
  private static String password;
  private static String email;

  public static String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public static String getEmail() {
    return email;
  }

  public User withUsername(String username) {
    this.username = username;
    return this;
  }

  public User withPassword(String password) {
    this.password = password;
    return this;
  }

  public User withEmail(String email) {
    this.email = email;
    return this;
  }

}
