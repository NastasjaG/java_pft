package get;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import com.jayway.restassured.RestAssured;

public class UsingRestAssured {

  @Test
  public void testResponse() throws IOException {

     ResponseFields ResponseFields = new ResponseFields();
    String status = createStatus(ResponseFields);
    Assert.assertEquals(status,"\"error\"");
  }
  private String createStatus(ResponseFields ResponseFields) {
    String json = RestAssured.given()
            .parameter("status", ResponseFields.getStatus())
            .get("https://newsapi.org/v2/top-headlines?source=time&apiKey=it_is_not_a_key").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("status").toString();
    }
}
