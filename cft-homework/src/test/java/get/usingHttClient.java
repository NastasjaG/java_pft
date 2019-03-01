package get;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Request;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class usingHttClient {


  @Test
  public void testResponse() throws IOException {
    Assert.assertEquals(getResponse().getStatus(),"error");
  }

  private responseFields getResponse() throws IOException {
    String json = Request.Get("https://newsapi.org/v2/top-headlines?source=time&apiKey=it_is_not_a_key")
            .execute().returnResponse().toString();

//HTTP/1.1 401 Unauthorized не дает вытащить содержание gson
    System.out.println(json);


    JsonElement parsed = new JsonParser().parse(json);
    return new Gson().fromJson(parsed,new TypeToken<responseFields>(){}.getType());
  }
}

