import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Test {

	public static void main(String[] args) throws IOException {

		// String ip = "google.com";
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = Test.class.getClassLoader().getResourceAsStream("config.properties");

			if (input == null) {
				System.out.println("Sorry, unable to find config file ");
				return;
			}
			prop.load(input);
			// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			// System.out.print("Enter Host : \n");
			// String host = br.readLine();

			String host = prop.getProperty("HOST");
			String command = prop.getProperty("ICMP_PING_COMMAND");
			String delay = prop.getProperty("ICMP_PING_DELAY");
			String tcommand = prop.getProperty("TRCRT_COMMAND");
			// icmpProto(command+" "+delay+" "+host);
			// System.out.println("icmp finished ");
			// tcpipProto(host);
			// System.out.println(" tcp ip finished");
			
			
			IcmpProtoThread ic = new IcmpProtoThread(command+" "+delay+" "+host);
			ic.start();
			TcpipProtoThread tc = new TcpipProtoThread(host);
			tc.start();			
			TraceRouteThread tr = new TraceRouteThread(tcommand+" "+host);
			tr.start();
			
			Report report = new Report();
			report.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
