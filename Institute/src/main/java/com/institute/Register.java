package com.institute;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		String name = req.getParameter("user_name");
		String email = req.getParameter("user_email");
		String pass = req.getParameter("user_pass");

		// JDBC Connection

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute", "root", "root");

			String query = "INSERT INTO student(name,email,password) VALUES(?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, pass);

			pstmt.executeUpdate();

			out.println("<h3>Data Stored Successfully in Database</h3>\n");
			out.println("<h4>Name: " + name + " </h4>");
			out.println("<h4>Name: " + email + " </h4>");
			out.println("<h4>Name: " + pass + " </h4>");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
