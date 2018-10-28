package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Information {
	
	private String status;
	
	private List<String> country;
	
	private JsonNode data;	
	
	
	
	
	public List<String> getCountry() {
		return country;
	}
	
	public void setCountry(List<String> country) {
		this.country = country;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JsonNode getData() {
		return data;
	}

	public void setData(JsonNode data) {
		this.data = data;
	}
	
	
	
}
