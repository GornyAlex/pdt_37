package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Alexander Gorny on 3/21/2017.
 */
public class SoapTests extends TestBase{

  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test issue")
            .withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }

  @Test
  public void testIfYouCanRunTestcase() throws RemoteException, ServiceException, MalformedURLException {
    int issueId = 37;
    skipIfNotFixed(issueId);
    System.out.println("Issue #" + issueId + " was closed. You can run this test.");
  }

  @Test
  public void testGetIssues() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.soap().getProjects();
    Set<Issue> issues = app.soap().getIssues(projects.iterator().next());
    System.out.println(issues.size());
    for (Issue issue : issues) {
      System.out.println("Issue #" + issue.getId() + " has status " + issue.getStatus().toString());
    }
  }

}
