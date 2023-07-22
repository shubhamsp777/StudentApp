package CrudOperations;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/addlink")
public class AddStudent extends HttpServlet {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String id=req.getParameter("studentid");
	String name=req.getParameter("studentname");
	String stream=req.getParameter("studentstream");
	//Parsing 
	int sid=Integer.parseInt(id);
	PreparedStatement pstmt=null;
	String query="insert into student_info values(?,?,?)";
	
	try {
		pstmt=con.prepareStatement(query);
		pstmt.setInt(1, sid);
		pstmt.setString(2, name);
		pstmt.setString(3,stream);
		int count=pstmt.executeUpdate();
		
		PrintWriter pw=resp.getWriter();
		pw.print("<h1>"+count+"Record Inserted Successfully</h1>");
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	

}
