import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.input.ReversedLinesFileReader;


public class pingHostByCommand {

	public static void main(String args[]) throws IOException {
		String commandToRun = "google.com";
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
				System.out.println("Pinging " + soc.getInetAddress());
				System.out.println(
						"Reply from " + soc.getInetAddress() + " String " + str1 + " Length : " + str1.length());
				System.out.println("Sent : " + str.length() + " Received : " + str1.length() + " Lost : "
						+ (str.length() - str1.length()));
				System.out.println("Approx. Time in Milliseconds = " + (t2 - t1));
				soc.close();

				// InetAddress address = InetAddress.getByName("oranum.com");
				// System.out.println("Name: " + address.getHostName());
				// System.out.println("Addr: " + address.getHostAddress());
				// System.out.println("Reach: " + address.isReachable(3000));
				//
				// Socket t = new Socket("google.com", 8080);
				// DataInputStream dis = new DataInputStream(t.getInputStream());
				// System.out.println("dis"+dis);
				// PrintStream ps = new PrintStream(t.getOutputStream());
				// System.out.println(" ps"+ps);
				// ps.println("Hello");
				// String str = dis.readLine();
				// System.out.println(" str "+str);
				// if (str.equals("Hello"))
				// System.out.println("Alive!");
				// else
				// System.out.println("Dead or echo port not responding");
				// t.close();

				// Socket t = new Socket("google.com", 7);
				// DataInputStream dis = new DataInputStream(t.getInputStream());
				// PrintStream ps = new PrintStream(t.getOutputStream());
				// ps.println("Hello");
				// String str = dis.readUTF();
				// if (str.equals("Hello")) {
				// System.out.println("Alive!");
				// }
				// else {
				// System.out.println("Dead");
				// }
				// t.close();
				// String s = "google.com";
				// InetAddress ia = InetAddress.getByName(s);
				// boolean reachable = ia.isReachable(1000);
				// logger.info("Is reachable"+reachable);
				// System.out.println(s + " is reachable: " + reachable);
				//
				// Socket sock = new Socket(s, 80);
				// sock.getOutputStream();
				// System.out.println(sock.getOutputStream());
				// sock.getOutputStream().write((byte) '\n');
				// int ch = sock.getInputStream().read();
				//
				// sock.close();
				// if (ch == '\n') {
				// System.out.println("pinged");
				// }
				//
				// System.out.println(s + " opened socket ok");

			

			} catch (Exception e) {
				e.printStackTrace();
				logger.info(e.toString());

			}
		}
	}

}
