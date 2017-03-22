package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Alexander Gorny on 3/21/2017.
 */
public class SoapHelper {

  private ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    return Arrays.asList(projects).stream()
            .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
            .collect(Collectors.toSet());
  }


  public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
//    return new MantisConnectLocator()
//            .getMantisConnectPort(new URL("http://localhost/mantisbt-2.2.0/api/soap/mantisconnect.php"));
    return new MantisConnectLocator()
            .getMantisConnectPort(new URL(app.getProperty("mantis.soapUrl")));
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
    IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
    return new Issue().withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                    .withName(createdIssueData.getProject().getName()));
  }


  public Set<Issue> getIssues(Project pr) throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    IssueData[] issues = mc.mc_project_get_issues(app.getProperty("web.adminLogin"),
            app.getProperty("web.adminPassword"), BigInteger.valueOf(pr.getId())
            , BigInteger.valueOf(1), BigInteger.valueOf(10));
    return Arrays.asList(issues).stream()
            .map((i) -> new Issue().withId(i.getId().intValue()).withSummary(i.getSummary())
                    .withStatus(i.getStatus().getName()).withDescription(i.getDescription()))
            .collect(Collectors.toSet());
  }
}

