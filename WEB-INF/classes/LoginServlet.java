import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/login", "root", "sai23bfa05193");

      PreparedStatement stmt = conn.prepareStatement(
        "SELECT * FROM users WHERE username = ? AND password = ?"
      );
      stmt.setString(1, username);
      stmt.setString(2, password);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        // ✅ Only redirect OR forward
        response.sendRedirect("welcome.html"); // this is enough
        // OR:
        // request.getRequestDispatcher("welcome.html").forward(request, response);
      } else {
        response.sendRedirect("login.html?error=Invalid+username+or+password");
      }

      rs.close();
      stmt.close();
      conn.close();

    } catch (Exception e) {
      // Show the error in browser too
      response.setContentType("text/html");
      response.getWriter().println("<h3>Login Failed: " + e.getMessage() + "</h3>");
      e.printStackTrace(); // good for console
    }
  }
}
