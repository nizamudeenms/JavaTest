import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;

public class Report extends Thread {

	@SuppressWarnings("unchecked")
	@Override
	public void run()  {
		Properties prop = new Properties();
		InputStream input = null;
		input = Test.class.getClassLoader().getResourceAsStream("config.properties");

		if (input == null) {
			System.out.println("Sorry, unable to find config file ");
			return;
		}
		try {
			prop.load(input);
			String host = prop.getProperty("HOST");

			JSONObject json = new JSONObject();
			json.put("host", host);

//			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//			HttpPost request = new HttpPost("http://yoururl");
//			StringEntity params = new StringEntity(json.toString());
//			request.addHeader("content-type", "application/json");
//			request.setEntity(params);
//			httpClient.execute(request);
			
			System.out.println("JSON Object : "+json.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
