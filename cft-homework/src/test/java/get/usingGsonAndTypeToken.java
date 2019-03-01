package get;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class usingGsonAndTypeToken {

  @Test
  public void testResponse() throws IOException {
     Assert.assertEquals(getResponse().getStatus(),"error");
      }

  private responseFields getResponse() throws IOException {
      String json = com.jayway.restassured.RestAssured.get("https://newsapi.org/v2/top-headlines?source=time&apiKey=it_is_not_a_key").asString();
      JsonElement parsed = new JsonParser().parse(json);
      return new Gson().fromJson(parsed,new TypeToken<responseFields>(){}.getType());
    }
      }






