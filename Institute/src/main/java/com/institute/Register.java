package com.institute;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class Register extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		String name = req.getParameter("user_name");
		String email = req.getParameter("user_email");
		String pass = req.getParameter("user_pass");

		Part part = req.getPart("image");
//		String fileName = part.getSubmittedFileName();
		String fileName = System.currentTimeMillis() + "_" + part.getSubmittedFileName();
//		System.out.println(fileName);

		// JDBC Connection

		try {
			Thread.sleep(1500);

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute", "root", "root");

			String query = "INSERT INTO student(name,email,password,imageName) VALUES(?,?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, pass);
			pstmt.setString(4, fileName);

			pstmt.executeUpdate();

//			Image Reading

			InputStream inputStream = part.getInputStream();
			byte[] imgByte = new byte[inputStream.available()];
			inputStream.read(imgByte);
			inputStream.close();

			// Create the 'images' directory if it doesn't exist
			File uploadDir = new File(req.getSession().getServletContext().getRealPath("/images"));
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

//			File Writing to Local Folder

//			String path = req.getRealPath("/" + "images" + File.separator + fileName);
			String path = req.getSession().getServletContext().getRealPath("/" + "images" + File.separator + fileName);
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(imgByte);
			fos.close();

			part.delete(); // Attempt to delete the temporary file

			out.println("done");

		} catch (Exception e) {
			e.printStackTrace();
			out.println("error");
		}

	}

}
