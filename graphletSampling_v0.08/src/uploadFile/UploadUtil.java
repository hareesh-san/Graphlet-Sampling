package uploadFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;


public class UploadUtil {
	private static String USER_NAME = "test@gmail.com";  
	private static String PASSWORD = "(&()"; 

	private static String TEXT_EXTENSIONS[] = { ".txt" };
	private static final String BLANK = "";
	/**
	 * check if file extension is text
	 * @param fileName
	 * @return
	 */
	public static boolean checkIfText(String fileName) {
		if (fileName == null || fileName.equals(BLANK))
			return false;

		boolean status = false;
		for (int j = 0; j < TEXT_EXTENSIONS.length; j++) {
			String sCurExtension = TEXT_EXTENSIONS[j];
			int len = fileName.length();
			int ext_len = sCurExtension.length();
			if (len >= ext_len) {
				String ext = fileName.substring(len - ext_len, len);
				if (ext.equalsIgnoreCase(sCurExtension)) {
					status = true;
				}
				break;
			}
		}
		return status;
	}

	/**
	 * check if data in file is correct or not
	 * @param data
	 * @return
	 */
	public static boolean correctData(String data) {
		if (data == null || data.equals(BLANK))
			return true;

		// return data.matches("\\d+");
		return data.matches("[0-9\\t\\s.]+");
	}

	/**
	 * this function launches cpp process and passes input and output file name
	 * @param inputFile
	 * @param outputFile
	 * @throws Exception
	 */
	public static void processFile(String inputFile, String outputFile,
			int Iterations, HttpServletResponse response)
	throws Exception {
		try {
			StringBuilder temp = new StringBuilder(UploadServlet.UPLOAD_DIR);
			String path = temp.append("Executable" + "/GUISE6.out").toString();
//			String path = temp.append("Executable"+"\\RandProb.exe").toString();
//			System.out.println("THe path is:"+path);
//			System.out.println("The input and output file path in the Util class are:"+inputFile+" "+outputFile);
			String[] CommandAndArgs = { path, inputFile, outputFile,
					Integer.toString(Iterations) };
			ProcessBuilder builder = new ProcessBuilder(CommandAndArgs);
			StringWriter infos = new StringWriter();
			StringWriter errors = new StringWriter();
			Process p = builder.start();
			// child process input stream, since data is piped to it from child
			// p output stream
			InputStream stdout = p.getInputStream();
			StreamBoozer seInfo = new StreamBoozer(stdout, new PrintWriter(
					infos, true));
			StreamBoozer seError = new StreamBoozer(p.getErrorStream(),
					new PrintWriter(errors, true));
			seInfo.start();
			seError.start();
			int retCode= p.waitFor();
//			System.out.println("Exit code of GUISE process is: "
//					+ retCode);
			retCode = 0; //overriding the error
			if(retCode!=0)
				throw new custException("GUISE process has retunred non zero:"+Integer.toString(retCode));
		} 
		catch (custException e ) {
			displayErrorPage(response, e);
		}
		catch (Exception e) {
			displayErrorPage(response, e);
		}
	}
	private static void displayErrorPage(HttpServletResponse response, Exception e)
	throws IOException {
		// TODO Auto-generated catch block
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// if some error occurs during processing file it 
		// will be propagated to UI
		out.println("<title>Error occured while processing file</title>"
				+ "<body bgcolor=FFFFFF>");
		out.println("<table border=\"1\">");
		out.println("<tr>");
		out.println("      <td><b>" + e.getMessage() + "</b>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");
		out.close();
	}

	public static void email(String outputFile, String email) {

		String from = USER_NAME;
		String pass = PASSWORD;
		String[] to = new String[1]; // list of recipient email addresses
		to[0]=email;
		String subject = "Graphlet sampelling results";
		int index=0;
		index=outputFile.lastIndexOf("\\");
		if(index<0)
		{
			index=outputFile.lastIndexOf("/");
		}
		outputFile=outputFile.substring(index+1);
		String body = "http://jamuna.cs.iupui.edu:8080/graphletSampling/UploadServlet?fname="+outputFile;

		sendFromGMail(from, pass, to, subject, body);

	}
	private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for( int i = 0; i < to.length; i++ ) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for( int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
		catch (AddressException ae) {
			ae.printStackTrace();
		}
		catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	public static int [] readdata(String outputFile) {
//		System.out.println(outputFile);
		BufferedReader br = null;
		String sCurrentLine;
		int [] data=null;
		try{
			// reading data from output file created by GUISE code
			
			br = new BufferedReader(new FileReader(outputFile));
			// writing data as response which will be displayed as output
			// storing line no, so as to skip name and email
			int lineNo = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				lineNo++;
				// first two lines have name and email
				// which need not be displayed as output
				if(lineNo >2){
					// reading data
					if(lineNo==11){
//					if(lineNo==4){
//						System.out.println("This is before forwaring the request");
						String[] strdata=sCurrentLine.split(" ");
						data=new int[strdata.length];
						for(int i=0;i<strdata.length;i++)
						{
							data[i]=Integer.parseInt(strdata[i]);
						}
						//String data= new StringBuilder(sCurrentLine).toString();
					}
				}
				// TODO Auto-generated method stub
			}}
		catch(Exception e)
		{
		}
		return data;

	}
}
