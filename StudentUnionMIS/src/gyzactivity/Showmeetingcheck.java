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
 * Servlet implementation class Showmeetingcheck
 */
@WebServlet("/Showmeetingcheck")
public class Showmeetingcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Showmeetingcheck() {
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
			PreparedStatement ptmt = conn.prepareStatement("select * from meetingcheck ");
			ptmt.execute();
			ResultSet rs = ptmt.executeQuery();
			response.setContentType("text/x-json");
			String j = "{\"rows\": [";
			while (rs.next()) {
				j += "{";
				j += "\"check_id\": \"" + rs.getString("check_id") + "\",";
				j += "\"meeting_date\": \"" + rs.getString("meeting_date") + "\",";
				j += "\"meeting_department\": \"" + rs.getString("meeting_department") + "\",";
				j += "\"meeting_theme\": \"" + rs.getString("meeting_theme") + "\",";
				j += "\"yingdao\": \"" + rs.getString("yingdao") + "\",";
				j += "\"shidao\": \"" + rs.getString("shidao") + "\",";
				j += "\"absent\": \"" + rs.getString("absent") + "\",";
				j += "\"qingjia\": \"" + rs.getString("qingjia") + "\"";
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
