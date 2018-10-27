package application;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SendMessage {
	
	OkHttpClient client = new OkHttpClient();
	private String url = "https://restcountries.eu/rest/v2/all?fields=name";
	  String runMS() throws IOException {
	    Request request = new Request.Builder()
	        .url(url)
	        .build();

	    try (Response response = client.newCall(request).execute()) {
	    	//System.out.println(response.body().string());
	      return response.body().string();
	      
	    }
	    }
	
}
