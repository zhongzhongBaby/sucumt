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
 * Servlet implementation class Showbill
 */
@WebServlet("/Showbill")
public class Showbill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Showbill() {
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
			PreparedStatement ptmt = conn.prepareStatement("select * from bill");
			ptmt.execute();
			ResultSet rs = ptmt.executeQuery();
			PreparedStatement stmt = conn.prepareStatement("select activity_name ,jingfeiyusuan from  activity where activity_id=?");
			stmt.setInt(1, 1);	
			ResultSet rrs = stmt.executeQuery();
			
			
			
			response.setContentType("text/x-json");
			String j = "{\"rows\": [";
			while (rs.next()) {

				j += "{";
				j += "\"id\": \"" + rs.getString("activity_id") + "\",";
				while (rrs.next()) {
				j += "\"activity_name\": \"" + rrs.getString("activity_name") + "\",";
				j += "\"jingfeiyusuan\": \"" + rrs.getString("jingfeiyusuan") + "\",";
				}
				j += "\"zanzhu\": \"" + rs.getString("bill_zanzhu") + "\",";
				j += "\"zongzhichu\": \"" + rs.getString("bill_pay") + "\"";
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
