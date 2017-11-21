import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TraceRouteThread extends Thread {
	private Thread t;
	private String commandToRun;

	TraceRouteThread(String command) {
		commandToRun = command;
	}

	@Override
	public void run() {
		try{
			System.out.println("traceroute command : " + commandToRun);
			Logger logger = Logger.getLogger("TracertLog");
			FileHandler fh;

			fh = new FileHandler("D:/logs/TRACERT.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.info("Beginning of TRACERT log");


			Process p = Runtime.getRuntime().exec(commandToRun);
			BufferedReader InputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String s = "";
			while ((s = InputStream.readLine()) != null) {
				System.out.println(s);
				logger.info(s);
			}
			System.out.println("Trace route finished");
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		Report report = new Report();
		report.start();
		
	}
}
