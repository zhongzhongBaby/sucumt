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
 * Servlet implementation class Showgoodszujie
 */
@WebServlet("/Showgoodszujie")
public class Showgoodszujie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Showgoodszujie() {
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
			PreparedStatement ptmt = conn.prepareStatement("select * from lendgoods");
			ptmt.execute();
			ResultSet rs = ptmt.executeQuery();
			response.setContentType("text/x-json");
			String j = "{\"rows\": [";
			while (rs.next()) {
				j += "{";
				j += "\"lend_id\": \"" + rs.getString("lend_id") + "\",";
				j += "\"returntime\": \"" + rs.getString("returntime") + "\",";
				j += "\"detail\": \"" + rs.getString("detail") + "\",";
				j += "\"lend_date\": \"" + rs.getString("lend_date") + "\",";
				j += "\"lend_danwei\": \"" + rs.getString("lend_danwei") + "\",";
				j += "\"lend_money\": \"" + rs.getString("lend_money") + "\",";
				j += "\"state\": \"" + rs.getString("state") + "\",";
				j += "\"return_date\": \"" + rs.getString("return_date") + "\"";
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
