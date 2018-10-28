package application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;




public class AppController implements Initializable{
	
	//private static final String URL = "https://restcountries.eu/rest/v2/all";
	//fx:controller="application.FXMLDocumentController"
	
	@FXML
	private Label lblCity, lblCountry;
	@FXML
	private Label lblTemperature,lblHumidity, lblPressure, lblWindSpeed, lblWindDirection; 
	@FXML
	private Button btnSearch;
	@FXML
	private Text txtMain;
	@FXML
	private TextField inpSearch;
	@FXML
	private MenuButton mBtnCountry; 
	@FXML
	private MenuButton mBtnRegion;
	@FXML
	private MenuButton mBtnCity;
	@FXML
	private MenuItem mBarFileExit;
	@FXML
	private ImageView ivWeather;
	
	
	String mResponse;
	String country;
	String region;
	String city;

	@Override
    public void initialize(URL location, ResourceBundle resources) {
		getCountryList();
		
	}
	
	@FXML
	private void mBarFileExit(ActionEvent event) {			    	
		    	Platform.exit();
		        System.exit(0);
	}

	
	@FXML
    private void getBtnSearch(ActionEvent event)  throws IOException{
		
	//GET INFO FROM SEARCHINPUT	
	String in = inpSearch.getText();
	
	
	getCountryList();	

	}

	public void getInfoW(String in){
		
	String url = "https://restcountries.eu/rest/v2/name/" + in + "?fields=name;capital;currencies;language;population;area";	
		   url = "https://restcountries.eu/rest/v2/all?fields=name";	
	
	
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
              if (!myResponse.isEmpty()){
            	  
            	 JsonNode arrNode = new ObjectMapper().readTree(myResponse);
              	List jsonArray = arrNode.findValues("name");

              }
              else{   
            	txtMain.setText("Sumting went wong, does this place exist?");
              }

          }
      }); 	
      //Call ended
      
	}
	
	
	
	//airvisual apikey: mHeiRK6EvwCqKo6Ea
	
	private void getCountryList(){
	
		mBtnCity.getItems().clear();
		
	String url = "http://api.airvisual.com/v2/countries?key=pP2e5BJYG3fwtHhdy";	
		//url = "https://api.airvisual.com/v2/cities?country="+in+"&key=pP2e5BJYG3fwtHhdy";
		//url="http://api.airvisual.com/v2/nearest_station?key=pP2e5BJYG3fwtHhdy";
		//url="http://api.airvisual.com/v2/cities?state=Dalarnas län&country="+in+"&key=pP2e5BJYG3fwtHhdy";
		//url="http://api.airvisual.com/v2/states?country="+in+"&key=pP2e5BJYG3fwtHhdy";
		//url="http://api.airvisual.com/v2/states?country="+in+"&key=pP2e5BJYG3fwtHhdy";
		//url="http://api.airvisual.com/v2/states?country="+in+"&key=pP2e5BJYG3fwtHhdy";
	
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
              if (!myResponse.isEmpty()){
	               	
              	ObjectMapper objectMapper = new ObjectMapper();
              	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
              	objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);     	
            	JsonNode arrNode = new ObjectMapper().readTree(myResponse).get("data");
            	List jsonArray = arrNode.findValues("country");
            	
            	
            	//Creating menu with countries
            	MenuItem mnItem;
            	for(Object country : jsonArray) {
            		mnItem = new MenuItem(country.toString().replace("\"", ""));     
            		mnItem.setOnAction(new EventHandler<ActionEvent>() {
            		    @Override 
            		    public void handle(ActionEvent e) {
            		    	
            		        //System.out.println(((MenuItem)e.getSource()).getText());
            		        String countryName = ((MenuItem)e.getSource()).getText();
            		        getRegionList(countryName); 
            		        mBtnCountry.setText(countryName);
            		        }
            		});
            	mBtnCountry.getItems().add(mnItem);             	
            	}   
            	
              }
              else{   
            	txtMain.setText("Sumting went wong, does this place exist?");
              }

          }
      }); 	
      //Call ended   
	}	
		
	private void getRegionList(String country) {
		
		
		mBtnRegion.getItems().clear();
		
		
		String url = "http://api.airvisual.com/v2/states?country="+country+"&key=pP2e5BJYG3fwtHhdy";	
		//System.out.println(url);
	
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
              if (!myResponse.isEmpty()){
              	//System.out.println(myResponse);  
	               	
              	ObjectMapper objectMapper = new ObjectMapper();
              	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
              	objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);     	
            	JsonNode arrNode = new ObjectMapper().readTree(myResponse).get("data");
            	List jsonArray = arrNode.findValues("state");
            	//System.out.println(jsonArray);
            	
            	
            	//Creating menu with countries
            	MenuItem mnItem;
            	for(Object countryO : jsonArray) {
            		mnItem = new MenuItem(countryO.toString().replace("\"", ""));     
            		mnItem.setOnAction(new EventHandler<ActionEvent>() {
            		    @Override 
            		    public void handle(ActionEvent e) {
            		       // System.out.println(((MenuItem)e.getSource()).getText());
            		        String region = ((MenuItem)e.getSource()).getText();
            		        getCityList(region, country);
            		        mBtnRegion.setText(region);
            		        mBtnCity.setText("City");
            		    }
            		});
            	mBtnRegion.getItems().add(mnItem);             	
            	}           	
              }
              else{   
            	txtMain.setText("Sumting went wong, does this place exist?");
              }

          }
      }); 
		
		
		
		
		
	}	
		
	private void getCityList(String region, String country) {
	
	
	mBtnCity.getItems().clear();
	
	
	String url="http://api.airvisual.com/v2/cities?state="+region+"&country="+country+"&key=pP2e5BJYG3fwtHhdy";
	//System.out.println(url);

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
          if (!myResponse.isEmpty()){
          	//System.out.println(myResponse);  
               	
          	ObjectMapper objectMapper = new ObjectMapper();
          	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
          	objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);     	
        	JsonNode arrNode = new ObjectMapper().readTree(myResponse).get("data");
        	List jsonArray = arrNode.findValues("city");
        	//System.out.println(jsonArray);
        	
        	
        	// JSONObject jsonObject = new JSONObject(myResponse);
        	// JSONArray dataObject = (JSONArray) jsonObject.get("data");
        	 //JSONArray countryArray = (JSONArray) dataObject.get("city");
        	 
        	 //System.out.print("---" + dataObject);
        	
        	//Creating menu with countries
        	MenuItem mnItem;
        	for(Object cityO : jsonArray) {
        		mnItem = new MenuItem(cityO.toString().replace("\"", ""));     
        		mnItem.setOnAction(new EventHandler<ActionEvent>() {
        		    @Override 
        		    public void handle(ActionEvent e) {
        		        //System.out.println(((MenuItem)e.getSource()).getText());
        		        String city = ((MenuItem)e.getSource()).getText();
        		        mBtnCity.setText(city);
        		        getWeatherInfo(country, region, city);
        		    }
        		});
        	mBtnCity.getItems().add(mnItem);             	
        	}           	
          }
          else{   
        	txtMain.setText("Sumting went wong, does this place exist?");
          }

      }
  }); 
	
	
	
	
	
}	
	
	private void getWeatherInfo(String country, String region, String city) {

		mBtnRegion.getItems().clear();
		
		//hu:humidity: %, pr:pressure:hPa, tp:temperature °C, wd:wind direction 360°, ws:wind speed m/s, ic:icon code
		
		
		String url = "http://api.airvisual.com/v2/city?city="+city+"&state="+region+"&country="+country+"&key=pP2e5BJYG3fwtHhdy";	
		//System.out.println(url);
	
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
              
              
              JSONObject jsonObject = new JSONObject(myResponse);

              if (!myResponse.isEmpty()){
              	//System.out.println(myResponse);  
	               	
              	ObjectMapper objectMapper = new ObjectMapper();
              	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
              	objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);  
              	 
            	JsonNode arrNode = new ObjectMapper().readTree(myResponse).get("data");            	

            	//System.out.println(arrNode);
           
            	JSONObject dataObject = (JSONObject) jsonObject.get("data");
            	JSONObject currentObject = (JSONObject) dataObject.get("current");           	
            	JSONObject weatherObject = (JSONObject) currentObject.get("weather");
            	
            	Platform.runLater(new Runnable() {
                    @Override public void run() {
                    	lblTemperature.setText(weatherObject.get("tp").toString());
                    	lblHumidity.setText(weatherObject.get("hu").toString());
                    	lblPressure.setText(weatherObject.get("pr").toString());
                    	lblWindSpeed.setText(weatherObject.get("ws").toString());
                    	lblWindDirection.setText(weatherObject.get("wd").toString());
                    	String imageURL = "/resources/"+ weatherObject.getString("ic") + ".png";
                    	String imageUrl = null;
						try {
							imageUrl = getClass().getResource(imageURL).toURI().toString();
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}                   	
                    	
                    	Image image = new Image(imageUrl);
                    	ivWeather.setImage(image);
                    
                    }
                });
            	        	
            	//System.out.println(weatherObject);
            	
              }
              else{   
            	txtMain.setText("Sumting went wong, does this place exist?");
              }

          }
      }); 
	}
	
	
	
}
	
	
	
	
	
	
	














