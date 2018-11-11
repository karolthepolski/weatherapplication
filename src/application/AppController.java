package application;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;




public class AppController implements Initializable{

	//fx:controller="application.FXMLDocumentController"
	
	@FXML
	private Label lblTemperature,lblHumidity, lblPressure, lblWindSpeed, lblWindDirection, lblCity; 
	@FXML
	private Text txtMain;
	@FXML
	private MenuItem mBarFileExit;
	@FXML
	private ImageView ivWeather,ivWindDirection;
	@FXML
	private Button btnCityMelbourne, btnCityMalaga, btnCityKrasnoyarsk, btnCityDubai, btnCityWashington, btnCityStockholm;


	String mResponse;
	String country;
	String region;
	String city;
	String url;

	@Override
    public void initialize(URL location, ResourceBundle resources) {	
	}
	
	@FXML
	private void mBarFileExit(ActionEvent event) {			    	
		    	Platform.exit();
		        System.exit(0);
	}


	@FXML
    private void btnCityMelbourne(ActionEvent event)  throws IOException{	
		SendRequest sr = new SendRequest();
		JSONObject weatherObject = sr.requestWeatherInfo("Australia", "Victoria", "Melbourne");
		updateUI(weatherObject, "Melbourne");
	}
	
	@FXML
    private void btnCityMalaga(ActionEvent event)  throws IOException{
		SendRequest sr = new SendRequest();
		JSONObject weatherObject = sr.requestWeatherInfo("Spain", "Andalucia", "Malaga");
		updateUI(weatherObject, "Malaga");
	}
	
	@FXML
    private void btnCityKrasnoyarsk(ActionEvent event)  throws IOException{
		SendRequest sr = new SendRequest();
		JSONObject weatherObject = sr.requestWeatherInfo("Russia", "Krasnoyarsk Krai", "Krasnoyarsk");
		updateUI(weatherObject, "Krasnoyarsk");
	}
	
	@FXML
    private void btnCityDubai(ActionEvent event)  throws IOException{
		SendRequest sr = new SendRequest();
		JSONObject weatherObject = sr.requestWeatherInfo("United Arab Emirates", "Dubai", "Dubai");
		updateUI(weatherObject, "Dubai");
	}
	
	@FXML
    private void btnCityWashington(ActionEvent event)  throws IOException{
		SendRequest sr = new SendRequest();
		JSONObject weatherObject = sr.requestWeatherInfo("USA", "Washington, D.C.", "Washington");
		updateUI(weatherObject, "Washington");
	}
	
	@FXML
    private void btnCityStockholm(ActionEvent event)  throws IOException{
		SendRequest sr = new SendRequest();
		JSONObject weatherObject = sr.requestWeatherInfo("Sweden", "Stockholm", "Stockholm");
		updateUI(weatherObject, "Stockholm");
	}
	
	private void updateUI(JSONObject weatherObject, String city) {
		//hu:humidity: %, pr:pressure:hPa, tp:temperature C grades, wd:wind direction 360 grades, ws:wind speed m/s, ic:icon code
		lblTemperature.setText(weatherObject.get("tp").toString()+"°C");
    	lblHumidity.setText(weatherObject.get("hu").toString()+"%");
    	lblPressure.setText(weatherObject.get("pr").toString()+"hPa");
    	lblWindSpeed.setText(weatherObject.get("ws").toString()+"m/s");
    	int wd = (int) weatherObject.get("wd");
    	lblWindDirection.setText(weatherObject.get("wd").toString()+"°");	
    	lblCity.setText(city);
    	    	
        Class<?> cs = this.getClass();        
        InputStream input = cs.getResourceAsStream("/resources/"+weatherObject.getString("ic") + ".png");       
        Image image = new Image(input);
    	ivWeather.setImage(image);
    	
    	input = cs.getResourceAsStream("/resources/winddirection.png");
    	image = new Image(input);
    	ivWindDirection.setImage(image);
    	ivWindDirection.setRotate(wd-90);
    	
    	
	}
	
}
	
	
	
	
	
	
	














