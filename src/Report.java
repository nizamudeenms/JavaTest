import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;

public class Report extends Thread {

	@SuppressWarnings("unchecked")
	@Override
	public void run() {

		Properties prop = new Properties();
		InputStream input = null;
		input = Test.class.getClassLoader().getResourceAsStream("config.properties");

		if (input == null) {
			System.out.println("Sorry, unable to find config file ");
			return;
		}
		try {
//			Thread.sleep(20000);
			prop.load(input);
			String host = prop.getProperty("HOST");

			JSONObject json = new JSONObject();
			json.put("host", host);

			// This comment to enabled once the URL to post is obtained.
			// CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			// HttpPost request = new HttpPost("http://yoururl");
			// StringEntity params = new StringEntity(json.toString());
			// request.addHeader("content-type", "application/json");
			// request.setEntity(params);
			// httpClient.execute(request);

			String icmp_data = " ";
			File file = new File("D:\\logs\\ICMP.log");
			int n_lines = 10;
			int counter = 0;
			ReversedLinesFileReader object = new ReversedLinesFileReader(file);
//			while (!object.readLine().isEmpty() && counter < n_lines) {
//				System.out.println("reading icmp object" + object.readLine());
//				icmp_data = icmp_data + " " + object.readLine();
//				counter++;
//			}
			for (int i = 0; i < n_lines; i++) {
				String line = object.readLine();
				if (line == null)
					break;
				icmp_data+= line;
			}
			System.out.println("icmp_data :" + icmp_data);
			json.put("icmp_ping", icmp_data);
			System.out.println("JSON Object : " + json.toString());
			object.close();
			// try (FileWriter jsonFile = new FileWriter("D:\\logs\\report.json")) {
			// jsonFile.write(json.toJSONString());
			// System.out.println("Successfully Copied JSON Object to File...");
			// System.out.println("\n JSON Object: " + json);
			// }

			String tracertData = " ";
			File tracertfile = new File("D:\\logs\\TRACERT.log");
			int tracert_counter = 0;
			ReversedLinesFileReader tracert_object = new ReversedLinesFileReader(tracertfile);
			// while (!tracert_object.readLine().isEmpty() && tracert_counter < n_lines) {
			// System.out.println("reading treacert"+tracert_object.readLine());
			// tracertData = tracertData+" "+ tracert_object.readLine();
			// tracert_counter++;
			// }
			for (int i = 0; i < n_lines; i++) {
				String line = tracert_object.readLine();
				if (line == null)
					break;
				tracertData+= line;
			}
			System.out.println("\n tracert :" + tracertData);
			json.put("trace", tracertData);
			System.out.println("JSON Object : " + json.toString());

			String tcpip_data = " ";
			File tcp_file = new File("D:\\logs\\TCPIP.log");
			int tcp_counter = 0;
			ReversedLinesFileReader tcp_object = new ReversedLinesFileReader(tcp_file);
//			while (!tcp_object.readLine().isEmpty() && tcp_counter < n_lines) {
//				System.out.println("reading tcp_object :" + tcp_object.readLine());
//				tcpip_data = tcpip_data + " " + tcp_object.readLine();
//				tcp_counter++;
//			}
			for (int i = 0; i < n_lines; i++) {
				String line = tcp_object.readLine();
				if (line == null)
					break;
				tcpip_data+= line;
			}
			System.out.println("\n tcpdata :" + tcpip_data);
			json.put("tcpip_ping", tcpip_data);
			System.out.println("JSON Object : " + json.toString());
			// try (FileWriter jsonFile = new FileWriter("D:\\logs\\report.json")) {
			// jsonFile.write(json.toJSONString());
			// System.out.println("Successfully Copied JSON Object to File...");
			// System.out.println("\n JSON Object: " + json);
			// }

			try (FileWriter jsonFile = new FileWriter("D:\\logs\\report.json")) {
				jsonFile.write(json.toJSONString());
				System.out.println("Successfully Copied JSON Object to File...");
				System.out.println("\n JSON Object: " + json);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
