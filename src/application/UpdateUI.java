package application;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;

public class UpdateUI {
	
	@FXML
	private MenuButton mBtnCountry; 
	@FXML
	private MenuButton mBtnRegion;
	@FXML
	private MenuButton mBtnCity;
	
	public void updateCountry(String s) {
		mBtnCountry.setText(s);
	}
	
	public void updateRegion(String s) {
		mBtnRegion.setText(s);
	}
	
	public void updateCity(String s) {
		mBtnCity.setText(s);
	}
	
}
