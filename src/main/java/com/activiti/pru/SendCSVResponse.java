package com.activiti.pru;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class SendCSVResponse {

	public static void doCSVResponse(HttpServletResponse response, File file, String fileName) {
		byte[] contents = fileToByte(file);
		String responseFile = fileName;
		response.setContentType("text/plain");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", responseFile);
		response.setHeader(headerKey, headerValue);
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			os.write(contents);
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				os.flush();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		response.setContentLength(contents.length);
		response.setHeader("Content-Disposition", "attachment;filename= " + responseFile);

		/*
		 * if (!file.delete()) { System.out.println("Unable to delete file"); }
		 */
	}
	private static byte[] fileToByte(File file) {
		FileInputStream fileInputStream = null;

		byte[] bFile = new byte[(int) file.length()];

		try {
			fileInputStream = new FileInputStream(file);
			int count = fileInputStream.read(bFile);
			fileInputStream.close();
			if (count > 0)
				return bFile;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bFile;
	}
}
