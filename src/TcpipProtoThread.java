import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TcpipProtoThread extends Thread {
	private Thread t;
	private String commandToRun;

	public TcpipProtoThread(String commandToRun) {
		super();
		this.commandToRun = commandToRun;
	}

	@Override
	public void run() {
		long t1, t2;
		Logger logger = Logger.getLogger("tcpipLog");
		FileHandler fh;
		try {
			fh = new FileHandler("D:/logs/TCPIP.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.info("Beginning of TCPIP log");
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (true) {
			try {
				System.out.println("tcip ip host:" + commandToRun);

				Socket soc = new Socket(commandToRun, 80);
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				String str = commandToRun;
				OutputStream os = soc.getOutputStream();
				PrintWriter pw = new PrintWriter(os, true);
				InputStream in = soc.getInputStream();
				InputStreamReader inr = new InputStreamReader(in);
				BufferedReader br1 = new BufferedReader(inr);
				t1 = System.currentTimeMillis();
				pw.println(str);

				String str1 = br1.readLine();
				t2 = System.currentTimeMillis();
				System.out.println("Pinging " + soc.getInetAddress() + " with string " + str);
				logger.info("Pinging " + soc.getInetAddress() + " with string " + str);
				System.out.println(
						"Reply from " + soc.getInetAddress() + " String " + str1 + " Length : " + str1.length());
				logger.info("Reply from " + soc.getInetAddress() + " String " + str1 + " Length : " + str1.length());
				System.out.println("Sent : " + str.length() + " Received : " + str1.length() + " Lost : "
						+ (str.length() - str1.length()));
				logger.info("Sent : " + str.length() + " Received : " + str1.length() + " Lost : "
						+ (str.length() - str1.length()));
				System.out.println("Approx. Time in Milliseconds  = " + (t2 - t1));
				logger.info("Approx. Time in Milliseconds  = " + (t2 - t1));
				soc.close();
			} catch (Exception e) {
				System.out.println("Error : " + e.getMessage());
				logger.info(e.getMessage());
			}
		}
	}

}
