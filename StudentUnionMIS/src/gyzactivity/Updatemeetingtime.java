package gyzactivity;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Updatemeetingtime
 */
@WebServlet("/Updatemeetingtime")
public class Updatemeetingtime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Updatemeetingtime() {
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
		
		String meetingtime=request.getParameter("meetingtime");
		String department_name=request.getParameter("repartment_name");
		
		PrintWriter out=response.getWriter();
		try {
			Connection conn = eb.javaweb.DBUtil.getConnection();
			PreparedStatement ptmt = conn.prepareStatement("update department set meetingtime=? where department_name=?");
			ptmt.setString(1, meetingtime);
			ptmt.setString(2, department_name);
			ptmt.execute();
			
	response.sendRedirect("showbumenxinxi.html");
		}
		catch (SQLException e) {
			out.print(e);
		}
		}	
			
		
	
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	
	
	
	}

}
