package application;

import java.io.IOException;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class SendRequest {
	
	OkHttpClient client = new OkHttpClient();
	String url;
	
	@FXML
	private Text txtMain;
	
	//airvisual apikey: mHeiRK6EvwCqKo6Ea
	JSONObject requestWeatherInfo(String country, String region, String city) throws IOException {
		  JSONObject weatherObject = null;
		  	url = "http://api.airvisual.com/v2/city?city="+city+"&state="+region+"&country="+country+"&key=pP2e5BJYG3fwtHhdy";
	        Request request = new Request.Builder()
	            .url(url)
	            .build();
      
	        String myResponse = client.newCall(request).execute().body().string();
	        
	        JSONObject jsonObject = new JSONObject(myResponse);

            if (!myResponse.isEmpty()){           		            	            	        
            	JSONObject dataObject = (JSONObject) jsonObject.get("data");
            	JSONObject currentObject = (JSONObject) dataObject.get("current");      
            	weatherObject = (JSONObject) currentObject.get("weather");
            	
            }
	        return weatherObject;       
	}	  
	  	 
}
