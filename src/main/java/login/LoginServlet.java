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
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/userdb";
//	private static final String LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
	Connection connection;
    
    public LoginServlet() {
        super();
        
    }

	public void init(ServletConfig config) throws ServletException {
		try {
			connection= DriverManager.getConnection(URL, USERNAME, PASSWORD); 
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("uname");
		String password = request.getParameter("pword");
		String query = "select * from uinfo where uname=? && pword=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,  username);
			ps.setString(2,  password);
			ResultSet rs = ps.executeQuery();
			PrintWriter pw = response.getWriter();
			pw.println("<html><body bgcolor=black text=white><center>");
			if(rs.next()) {
				pw.println("Welcome : " +username);
			} else {
				pw.println("User not valid!");
			}
			pw.println("</center></body></html>");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
