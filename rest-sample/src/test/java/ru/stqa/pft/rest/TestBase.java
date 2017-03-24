package ru.stqa.pft.rest;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.appmanager.ApplicationManager;
import ru.stqa.pft.model.Issue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created by Alexander Gorny on 3/23/2017.
 */
public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager();

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  boolean isIssueOpen(int issueId) throws RemoteException, MalformedURLException{
    String status = "";
    System.out.println(app.getProperty("web.issueUrl"));
    String json = RestAssured.get(app.getProperty("web.issueUrl") + issueId + ".json").getBody().asString();
    JsonObject parsed = new JsonParser().parse(json).getAsJsonObject();
    JsonArray issues = parsed.getAsJsonArray("issues");
    for (JsonElement state_name : issues) {
      JsonObject statuses = state_name.getAsJsonObject();
      status = statuses.get("state_name").toString().replaceAll("\"", "");
      System.out.println("Task is - " + status);
    }
    return !status.equals("Resolved");
  }


  public void skipIfNotFixed(int issueId) throws RemoteException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }


  public Executor getExecutor() {
    return Executor.newInstance().auth(app.getProperty("web.username"), app.getProperty("web.password"));
  }


  public int createIssue(Issue newIssue) throws IOException {

    String json = getExecutor().execute(Request.Post(app.getProperty("web.baseUrl"))
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return  parsed.getAsJsonObject().get("issue_id").getAsInt();
  }


  public Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get(app.getProperty("web.baseUrl"))).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }


  public Set<Issue> getIssuesWithRestAssured() throws IOException {
    String json =  RestAssured.get(app.getProperty("web.baseUrl")).asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }


  public int createIssueWithRestAssured(Issue newIssue) throws IOException {
    String json =  RestAssured.given()
            .parameter("subject", newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post(app.getProperty("web.baseUrl")).asString();
    JsonElement parsed = new JsonParser().parse(json);
    return  parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

}
