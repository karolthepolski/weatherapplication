package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class UpdateUI {
	
	@FXML
	private MenuButton mBtnCountry; 
	@FXML
	private MenuButton mBtnRegion;
	@FXML
	private MenuButton mBtnCity;
	
	private InformationLists information = new InformationLists();
	
	public void updateCountry(String s) {
		mBtnCountry.setText(s);
	}
	
	public void updateRegion(String s) {
		mBtnRegion.setText(s);
	}
	
	public void updateCity(String s) {
		mBtnCity.setText(s);
	}
	
	public void updateAll(String country, String region, String city) {
		mBtnCountry.setText(country);
		mBtnRegion.setText(region);
		mBtnCity.setText(city);
	}
	
	public void clearItems() {
		mBtnCity.getItems().clear();
	}
	
	public void buildCountryListMenu() {
		MenuItem mnItem;
    	for(Object country : information.getCountrylist()) {
    		mnItem = new MenuItem(country.toString().replace("\"", ""));     
    		mnItem.setOnAction(new EventHandler<ActionEvent>() {
    		    @Override 
    		    public void handle(ActionEvent e) {
    		    	
    		        System.out.println(((MenuItem)e.getSource()).getText());
    		        String countryName = ((MenuItem)e.getSource()).getText();
    		        information.setRegionlist(countryName); 
    		        buildRegionListMenu(countryName);
    		        mBtnCountry.setText(countryName);
    		        }
    		});
    	mBtnCountry.getItems().add(mnItem);             	
    	} 
	}
	
	public void buildRegionListMenu(String country) {
		mBtnRegion.getItems().clear();
		MenuItem mnItem;
    	for(Object countryO : information.getCitylist()) {
    		mnItem = new MenuItem(countryO.toString().replace("\"", ""));     
    		mnItem.setOnAction(new EventHandler<ActionEvent>() {
    		    @Override 
    		    public void handle(ActionEvent e) {
    		        System.out.println(((MenuItem)e.getSource()).getText());
    		        String region = ((MenuItem)e.getSource()).getText();
    		        information.setCityList(region, country);
    		        
    		        mBtnRegion.setText(region);
    		        mBtnCity.setText("City");
    		    }
    		});
    	mBtnRegion.getItems().add(mnItem);             	
    	} 
	}
	
}
