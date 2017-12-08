package org.jspider.webApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

//@WebServlet("/reg")
public class RegisterServlet extends GenericServlet {
	Connection con;
	PreparedStatement psmt;
	PrintWriter out;

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		double percentage = Double.parseDouble(req.getParameter("perc"));
		String stream = req.getParameter("steam");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "pba206");
			psmt = con.prepareStatement("insert into db.studentinfo values(?,?,?,?)");
			psmt.setInt(1, id);
			psmt.setString(2, name);
			psmt.setDouble(3, percentage);
			psmt.setString(4, stream);
			int res = psmt.executeUpdate();
			out = resp.getWriter();
			if(res>0) {
				String htmlOut="<html>"+"<body>"+"<h1>"+"Welcome "+name+" You are successfully Registered"+"<br>"+"</h1>"+"</body>"+"</html>";

				out.println(htmlOut);
			}
			else {
				String htmlOut="<html>"+"<body>"+"<h1>"+"Registration Failed"+"<br>"+"</h1>"+"</body>"+"</html>";
				out.println(htmlOut);
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

}
