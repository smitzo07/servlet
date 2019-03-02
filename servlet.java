import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

@WebServlet("/valserv")
public class valserv extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pccoe", "root","mysql");
			
			
		
			Statement stmt=(Statement) con.createStatement();
			int i=stmt.executeUpdate("insert into student values('"+username+"','"+password+"');");
			if(i<0)
			{
				writer.println("Failed to insert");
			}
			else
			{
				writer.println("Inserted into database successfully...");
			}
        } catch (Exception e) {
        	response.sendError(503, "PROBLEM IN DATABASE...");
        }
	}
		  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        doGet(request, response);
		    }
}
