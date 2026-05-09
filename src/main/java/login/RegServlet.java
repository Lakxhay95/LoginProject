package login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;


@WebServlet("/reg")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//	private static final String LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/userdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	Connection connection;
    public RegServlet() {
        super();
    }


	public void init(ServletConfig config) throws ServletException {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String uname = request.getParameter("uname");
		String pword = request.getParameter("pword");
		String query = "insert into uinfo values(?,?,?,?)";
		try {
			PreparedStatement ps= connection.prepareStatement(query);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, uname);
			ps.setString(4, pword);
			ps.executeUpdate();
			
			PrintWriter pw = response.getWriter();
			pw.println("<html><body bgcolor=black text=white><center>");
			pw.println("<h2>Registration Successfull</h2>");
			pw.println("<a href=login.html> Login </a>");
			pw.println("</center></body></html>");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
