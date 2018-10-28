package application;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InformationLists {
	private List countrylist;
	private List regionlist;
	private List citylist;
	

	
	
	public List getCountrylist() {
		return countrylist;
	}
	
	public List getRegionlist() {
		return countrylist;
	}
	
	public List getCitylist() {
		return countrylist;
	}
	
	
	public void setCountrylist() {
		String url = "http://api.airvisual.com/v2/countries?key=pP2e5BJYG3fwtHhdy";	
		Request request = new Request.Builder()
	            .url(url)
	            .build();
	    OkHttpClient client = new OkHttpClient();
	    
	    client.newCall(request).enqueue(new Callback() {
	        @Override
	        public void onFailure(Call call, IOException e) {
	            call.cancel();
	        }
	        @Override
	        public void onResponse(Call call, Response response) throws IOException {
	            final String myResponse = response.body().string();
	                          	
	            	ObjectMapper objectMapper = new ObjectMapper();
	            	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	            	objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);     	
	            	JsonNode arrNode = new ObjectMapper().readTree(myResponse).get("data");
	            	countrylist = arrNode.findValues("country");	
	            	System.out.println(countrylist);
	            
	        }
	        
	    });
	}
	
	public void setRegionlist(String country) {
		String url = "http://api.airvisual.com/v2/states?country="+country+"&key=pP2e5BJYG3fwtHhdy";	
		System.out.println(url);
	
	  Request request = new Request.Builder()
              .url(url)
              .build();
      OkHttpClient client = new OkHttpClient();
      
      //Makecall
      client.newCall(request).enqueue(new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {
              call.cancel();
          }
          @Override
          public void onResponse(Call call, Response response) throws IOException {
              final String myResponse = response.body().string();
               	
              	ObjectMapper objectMapper = new ObjectMapper();
              	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
              	objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);     	
            	JsonNode arrNode = new ObjectMapper().readTree(myResponse).get("data");
            	regionlist = arrNode.findValues("state");        	
          }
      }); 
	}	
	
	public void setCityList(String country, String region) {
		String url="http://api.airvisual.com/v2/cities?state="+region+"&country="+country+"&key=pP2e5BJYG3fwtHhdy";
		System.out.println(url);

	  Request request = new Request.Builder()
	          .url(url)
	          .build();
	  OkHttpClient client = new OkHttpClient();
	  
	  //Makecall
	  client.newCall(request).enqueue(new Callback() {
	      @Override
	      public void onFailure(Call call, IOException e) {
	          call.cancel();
	      }
	      @Override
	      public void onResponse(Call call, Response response) throws IOException {
	          	final String myResponse = response.body().string();	             	
	          	ObjectMapper objectMapper = new ObjectMapper();
	          	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	          	objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);     	
	        	JsonNode arrNode = new ObjectMapper().readTree(myResponse).get("data");
	        	citylist = arrNode.findValues("city");
	        	       	
	      }
	  });
		
	}
}
