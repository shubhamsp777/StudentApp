package CrudOperations;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/display")
public class DisplayStudent extends HttpServlet {

	Connection con;
	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja7","root","sql@123");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Statement stmt=null;
		ResultSet rs=null;
		PrintWriter pw=resp.getWriter();
		String query="select * from student_info";
		
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			pw.print("<table border='2'>");
			pw.print("<tr>");
			pw.print("<th>Student id</th>");
			pw.print("<th>Student Name</th>");
			pw.print("<th>Student Stream</th>");
			pw.print("</tr>");
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String stream=rs.getString(3);
				pw.print("<tr>");
				pw.print("<th>"+id+"</th>");
				pw.print("<th>"+name+"</th>");
				pw.print("<th>"+stream+"</th>");
				pw.print("</tr>");
				
			}
			pw.print("</table>");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
