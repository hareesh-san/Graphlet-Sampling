package uploadFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 * Servlet implementation class UploadServlet
 */

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String UPLOAD_DIR;
	private static final String PREFIX_FILE_INPUT = "input";
	private static final String PREFIX_FILE_OUTPUT = "output";
	private static final String SUFFIX_FILE = ".txt";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String rel=request.getParameter("fname");
		String absoluteDiskPath = getServletContext().getRealPath(rel);
//		System.out.println("Absolute path is:"+absoluteDiskPath);
		int [] data=UploadUtil.readdata(absoluteDiskPath);
		HttpSession session = request.getSession();
		//System.out.println(data);
		session.setAttribute("GFDString",data);
		RequestDispatcher rd=request.getRequestDispatcher("/amchart/jspFiles/chartSelection.jsp");
		rd.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		System.out.println("came here");
		String contentType = request.getContentType();
		String saveFile = "";
		// for upload, content type should be multipart/form-data
		if ((contentType != null)
				&& (contentType.indexOf("multipart/form-data") >= 0)) {
			String filename = null;
			long filesize = 0;
			byte dataBytes[] = null;
			String name = null;
			String email = null;
			boolean checked=false;
			int Iterations=0;
			
			try {
				// finds all the items present in request
				List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				// iterating over items
//				System.out.println(items);
				for (FileItem item : items) {
					if (item.isFormField()) {
						// Process regular form field (input type="text|radio|checkbox|etc", select, etc).
						String fieldname = item.getFieldName();
						String fieldvalue = item.getString();
						// extracting name
						if("name".equals(fieldname)){
							name = fieldvalue;
						}
						// extracting email
						else if("email".equals(fieldname)){
							email = fieldvalue;
						}
						else if("sendmail".equals(fieldname)){
							checked=true;
						}
						// extracting number of iterations
						else if("Iterations".equals(fieldname)){
							Iterations = Integer.parseInt(fieldvalue);	
						}
					}
					else{
						// Process form file field (input type="file").
						filename = FilenameUtils.getName(item.getName());
						filesize = item.getSize();
						dataBytes = item.get();
//						System.out.println("came here");
//						System.out.println("The file size is: "+filesize);
					}
				}
			} catch (FileUploadException e) {
				throw new ServletException("Cannot parse multipart request.", e);
			}
			String file = new String(dataBytes);
			boolean error = false;
			if (UploadUtil.checkIfText(filename)) {
				// checking for the file size, it should be less than 200MB
//				System.out.println("checking if data content is correct");
				if (filesize / 1024 / 1024 < 200) {
					// checking if data content is correct
					// verify if it contains only digits and white spaces
					if (UploadUtil.correctData(file)) {
					} else {
						request.setAttribute("errorMessage","File can only contain digits and whitespaces");
						request.getRequestDispatcher("/welcome.jsp").forward(request, response);
						error = true;
					}
				} else {
					// if file size is more than 200MB
					// forward request back to welcome.jsp
					// setting error message in errorMessage component
					request.setAttribute("errorMessage",
					"File size should be less than 200MB");
					request.getRequestDispatcher("/welcome.jsp").forward(
							request, response);
					error = true;
				}
			} else {
				// if file extension is not text
				// forward request back to welcome.jsp
				// setting error message in errorMessage component
				request.setAttribute("errorMessage","Only text file can be uploaded");
				request.getRequestDispatcher("/welcome.jsp").forward(request,response);
				error = true;
			}
//			System.out.println("THis is after all validations inside the server ");
			// if no error, then save it 
			if (!error) {
				UPLOAD_DIR = getServletContext().getRealPath("/");
				// output file folder
				saveFile = UPLOAD_DIR + saveFile;
				// storing data to input file
				// this is temporary file
				try {
				File tmpInpFile = File.createTempFile(PREFIX_FILE_INPUT,
						SUFFIX_FILE, new File(UPLOAD_DIR));
//				System.out.println("This is after creating input file ");
				// creating output file which will be passed to RanProb
				File tmpOutFile = File.createTempFile(PREFIX_FILE_OUTPUT,
						SUFFIX_FILE, new File(UPLOAD_DIR));
//				System.out.println("This is after creating output file ");
				FileOutputStream inFile = new FileOutputStream(tmpInpFile);
				inFile.write(dataBytes);
				inFile.flush();
				inFile.close();
					// output file name
					String outputFile = tmpOutFile.getAbsolutePath();
					// writing name and email to output file
					// creating file writer
					FileWriter outFile = new FileWriter(tmpOutFile, true);
					// creating buffered writer
					BufferedWriter bw = new BufferedWriter(outFile);
					// writing name
					bw.write(name);	
					// newline
					bw.newLine();
					// writing email
					bw.write(email);
					// newline
					bw.newLine();
					bw.close();
//					System.out.println("calling GUISE.out process which takes input file and output file");
					// calling GUISE.out process which takes input file and output file
					UploadUtil.processFile(tmpInpFile.getAbsolutePath(),
							outputFile,Iterations,response);
//					int [] data=UploadUtil.readdata(outputFile);
					int [] data= {17, 20, 13, 35, 24, 76, 82, 53,
									19, 22, 15, 37, 26, 78, 84, 55,
										15, 18, 13, 39, 22, 70, 14, 56,
											11, 10, 14, 43, 32};
					if(checked)
						UploadUtil.email(outputFile,email);
					HttpSession session = request.getSession();
					//System.out.println(data);
					session.setAttribute("GFDString",data);
					RequestDispatcher rd=request.getRequestDispatcher("/amchart/jspFiles/chartSelection.jsp");
					rd.forward(request,response);
					}
						catch (Exception e) {
					displayErrorPage(response, e);
				}
			}
		}
	}

	/**
	 * @param response
	 * @param e
	 * @throws IOException
	 */
	private void displayErrorPage(HttpServletResponse response, Exception e)
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
}
