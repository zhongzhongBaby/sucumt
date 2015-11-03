package gyzactivity;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Showactivityask
 */
@WebServlet("/Showactivityask")
public class Showactivityask extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Showactivityask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out=response.getWriter();
		try {
			response.setContentType("text/x-json;charset=utf-8");
			Connection conn = eb.javaweb.DBUtil.getConnection();
			PreparedStatement ptmt = conn.prepareStatement("select * from activity");
			ptmt.execute();
			ResultSet rs = ptmt.executeQuery();
			
			
			response.setContentType("text/x-json");
			String j = "{\"rows\": [";
			while (rs.next()) {

				j += "{";
				j += "\"id\": \"" + rs.getString("activity_id") + "\",";
				j += "\"activity_name\": \"" + rs.getString("activity_name") + "\",";
				j += "\"activity_state\": \"" + rs.getString("state") + "\",";
				j += "\"laststate\": \"" + rs.getString("laststate") + "\",";
				j += "\"activity_addr\": \"" + rs.getString("activity_address") + "\"";
				j += "}";
				j += ", ";

			}

			if (j != "{\"rows\": [") {
				j = j.substring(0, j.length() - 2);
			}

			j += "]}";

			out.print(j);
			ptmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
