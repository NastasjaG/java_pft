package get;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import com.jayway.restassured.RestAssured;

public class usingRestAssured {

  @Test
  public void testResponse() throws IOException {

     responseFields responseFields = new responseFields();
    String status = createStatus(responseFields);
    Assert.assertEquals(status,"\"error\"");
  }
  private String createStatus(responseFields responseFields) {
    String json = RestAssured.given()
            .parameter("status", responseFields.getStatus())
            .get("https://newsapi.org/v2/top-headlines?source=time&apiKey=it_is_not_a_key").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("status").toString();
    }
}
