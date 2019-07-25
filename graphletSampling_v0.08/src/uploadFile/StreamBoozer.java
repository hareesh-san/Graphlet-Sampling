package uploadFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class StreamBoozer extends Thread {
	private InputStream in;
	private PrintWriter pw;

	public StreamBoozer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param in
	 * @param pw
	 */
	public StreamBoozer(InputStream in, PrintWriter pw) {
		this.in = in;
		this.pw = pw;
	}

	/**
	 * launches a thread
	 */
	@Override
	public void run() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = br.readLine()) != null) {
				pw.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
