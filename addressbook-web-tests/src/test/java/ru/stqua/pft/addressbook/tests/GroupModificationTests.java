package ru.stqua.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.model.GroupData;

import java.util.Set;

/**
 * Created by Alexander Gorny on 1/24/2017.
 */
public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification(){
    Set<GroupData> before = app.group().all();
    GroupData dmodifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(dmodifiedGroup.getId()).withName("test2").withHeader("test2").withFooter("test3");
    app.group().modify(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(dmodifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after);
  }


}
