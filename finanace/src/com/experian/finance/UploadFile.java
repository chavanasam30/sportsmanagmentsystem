package com.experian.finance;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.experian.finance.main.FinanceMain;

public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String UPLOAD_DIRECTORY = "C:/financeutility";
	private ServletFileUpload uploader = null;

	@Override
	public void init() throws ServletException {
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute(
				"FILES_DIR_FILE");
		diskFileItemFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(diskFileItemFactory);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String fileName = req.getParameter("fileName");
		if (fileName == null || fileName.equals("")) {
			System.out.println("File Not found");		
		}
		File file = new File(getServletContext().getAttribute("FILES_DIR")
				+ File.separator + fileName);
		System.out
				.println("File location on server::" + file.getAbsolutePath());
		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
			String mimeType = ctx.getMimeType(file.getAbsolutePath());
			resp.setContentType(mimeType != null ? mimeType
					: "application/octet-stream");
			resp.setContentLength((int) file.length());
			resp.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");
			ServletOutputStream os = resp.getOutputStream();
			byte[] bufferData = new byte[1024];
			int read = 0;
			while ((read = fis.read(bufferData)) != -1) {
				os.write(bufferData, 0, read);
			}
			os.flush();
			os.close();
			fis.close();
			System.out.println("File downloaded at client successfully");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		File file1 = null;
		String theString = "";
		String fieldName;
		String fieldValue = null;
		String fileName = "";

		System.out.println("********* " + System.getProperty("catalina.home"));
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			resp.setContentType("text/html");
			if (isMultipart) {
				List<FileItem> items = uploader.parseRequest(req);

				for (FileItem item : items) {
					if (!item.isFormField()) {
						// Process form file field (input type="file").
						fieldName = item.getFieldName();
						fileName = FilenameUtils.getName(item.getName());
						System.out.println("fileName : " + fileName);
						InputStream fileContent = item.getInputStream();
						if (fileContent != null) {
							// file1= new
							// File(UPLOAD_DIRECTORY+File.separator+"input"+File.separator+fileName);
							System.out.println(getServletContext()
									.getAttribute("FILES_DIR"));
							file1 = new File(getServletContext().getAttribute(
									"FILES_DIR")
									+ File.separator + fileName);
							StringWriter writer = new StringWriter();
							IOUtils.copy(fileContent, writer, "ISO-8859-1");
							theString = writer.toString();
							System.out
									.println("**************** INPUT File ************ ");
							System.out.println(theString);
						}

						if (!file1.exists()) {
							
							file1.createNewFile();

							FileUtils.writeStringToFile(file1, theString);
							System.out
									.println("########### TEMP INPUT File Created ##############");
							System.out.println(theString);
						}

					} else {
						// Process regular form field (input
						// type="text|radio|checkbox|etc", select, etc).
						fieldName = item.getFieldName();
						fieldValue = item.getString();
						System.out.println("fieldName: " + fieldName
								+ " fieldValue: " + fieldValue);
						if (null != file1 && null != fieldValue) {
							if (!fieldValue.equalsIgnoreCase("")) {

								boolean outfileFlag = new FinanceMain()
										.inputOutputFile(file1, fieldValue);

								if (outfileFlag) {
									if (null != fieldValue
											&& fieldValue
													.equalsIgnoreCase("Cancel")) {
										req.setAttribute("flag", "true");
										req.setAttribute("fieldValue", "Cancel");
									} else if (null != fieldValue
											&& fieldValue
													.equalsIgnoreCase("Register")) {
										req.setAttribute("flag", "true");
										req.setAttribute("fieldValue",
												"Register");
									}
									String uri = "/Finance/uploadFile?fileName=out_"
											+ file1.getName();
									System.out.println("URI  " + uri);
									req.setAttribute("url", uri);
								} else {
									req.setAttribute("flag", "false");
									req.setAttribute("Error", "File not available for download.");
								}

							}
						}
					}

				}
			}

		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse multipart request.", e);
		}

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("MeterInput.jsp");
		dispatcher.include(req, resp);

		// ==========================================================================================================================//

	}

}
