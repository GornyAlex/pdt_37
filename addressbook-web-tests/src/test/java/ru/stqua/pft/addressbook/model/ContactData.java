package ru.stqua.pft.addressbook.model;

public class ContactData {
  private final String firsName;
  private final String lastName;
  private final String nickname;
  private final String title;
  private final String company;
  private final String address;
  private final String mobilePhone;
  private final String email1;
  private final String email2;
  private final String urlHomePage;

  public ContactData(String firsName, String lastName, String nickname, String title, String company, String address, String mobilePhone, String email1, String email2, String urlHomePage) {
    this.firsName = firsName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.mobilePhone = mobilePhone;
    this.email1 = email1;
    this.email2 = email2;
    this.urlHomePage = urlHomePage;
  }

  public String getFirsName() {
    return firsName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getUrlHomePage() {
    return urlHomePage;
  }
}
