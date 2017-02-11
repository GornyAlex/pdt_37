package ru.stqua.pft.addressbook.model;

public class ContactData {
  private int id;
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
  private String group;

  public ContactData(int id, String firsName, String lastName, String nickname, String title, String company, String address, String mobilePhone, String email1, String email2, String urlHomePage, String group) {
    this.id = id;
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
    this.group = group;
  }

  public ContactData( String firsName, String lastName, String nickname, String title, String company, String address, String mobilePhone, String email1, String email2, String urlHomePage, String group) {
    this.id = Integer.MAX_VALUE;
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
    this.group = group;
  }

  public int getId() {
    return id;
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

  public String getGroup() {
    return group;
  }


  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firsName='" + firsName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firsName != null ? !firsName.equals(that.firsName) : that.firsName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = firsName != null ? firsName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }
}
