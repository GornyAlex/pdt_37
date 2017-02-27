package ru.stqua.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqua.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Gorny on 2/26/2017.
 */
public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")){
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")){
      saveAsXml(contacts, new File(file));
    } if (format.equals("json")){
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }

  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)){
      for (ContactData contact : contacts){
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                contact.getFirsName(),
                contact.getLastName(),
                contact.getNickname(),
                contact.getCompany(),
                contact.getTitle(),
                contact.getAddress(),
                contact.getHomePhone(),
                contact.getMobilePhone(),
                contact.getWorkPhone(),
                contact.getEmail(),
                contact.getEmail2(),
                contact.getEmail3(),
                contact.getUrlHomePage()));
      }
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)){
      writer.write(xml);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)){
      writer.write(json);
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData()
              .withFirsName(String.format("First Name %s", i))
              .withLastName(String.format("Last Name %s", i))
              .withNickname(String.format("Nickname %s", i))
              .withCompany(String.format("Company %s", i))
              .withTitle(String.format("Title %s", i))
              .withAddress(String.format("%s%s Street", i, i))
              .withHomePhone(String.format("%s%s%s-222-3333", i, i, i))
              .withMobilePhone(String.format("222-%s%s%s-3333", i, i, i))
              .withWorkPhone(String.format("333-444-%s%s%s%s", i, i, i, i))
              .withEmail(String.format("email%s@mail%s.com", i, i))
              .withEmail2(String.format("email%s@mail%s.com", i+1, i+1))
              .withEmail3(String.format("email%s@mail%s.com", i+2, i+2))
              .withUrlHomePage(String.format("www.homepage%s.com", i)));
    }
    return contacts;
  }

}
