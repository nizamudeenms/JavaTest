import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class IcmpProtoThread extends Thread {
	private Thread t;
	private String commandToRun;

	public IcmpProtoThread(String commandToRun) {
		this.commandToRun = commandToRun;
	}

	@Override
	public void run() {
		try {
			System.out.println("command" + commandToRun);
			Logger logger = Logger.getLogger("icmpLog");
			FileHandler fh;
			Process p = Runtime.getRuntime().exec(commandToRun);
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String s = "";
			fh = new FileHandler("D:/logs/ICMP.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.info("Beginning of ICMP log");


			// reading output stream of the command
			while ((s = inputStream.readLine()) != null) {
				System.out.println(s);
				// the following statement is used to log any messages
				logger.info("s : " + s);

			}
			System.out.println("ICMP  finished");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
