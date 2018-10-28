package application;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestClass {
	private static final HttpUrl URL = null;

	/*void restCheckEmailAndPassword(String email, String password) throws IOException {

        

        Request request = new Request.Builder()
                .url(URL)
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
                if (!myResponse.isEmpty()){
                    

                }
                else{
               
                }

            }
        });
    }*/
}
