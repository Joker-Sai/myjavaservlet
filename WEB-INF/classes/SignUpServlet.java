import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3307/login", "root", "sai23bfa05193"
      );

      PreparedStatement stmt = conn.prepareStatement(
        "INSERT INTO users (username, password) VALUES (?, ?)"
      );
      stmt.setString(1, username);
      stmt.setString(2, password);
      stmt.executeUpdate();

      // ✅ redirect to login on success
      response.sendRedirect("login.html");

      stmt.close();
      conn.close();
    } catch (Exception e) {
      // ✅ send visible error
      response.setContentType("text/html");
      response.getWriter().println("<h3>Signup Failed: " + e.getMessage() + "</h3>");
      e.printStackTrace(); // also print to console for debugging
    }
  }
}
