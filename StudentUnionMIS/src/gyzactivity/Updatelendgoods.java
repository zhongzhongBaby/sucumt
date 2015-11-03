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
 * Servlet implementation class Updatelendgoods
 */
@WebServlet("/Updatelendgoods")
public class Updatelendgoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Updatelendgoods() {
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
		
		Date now=new Date();
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
		System.out.print(format.format(now));
		
		Integer lend_id=Integer.parseInt(request.getParameter("lend_id"));
		PrintWriter out=response.getWriter();
		try {
			Connection conn = eb.javaweb.DBUtil.getConnection();
			PreparedStatement ptmt = conn.prepareStatement("update lendgoods set state=?,returntime=? where lend_id=?");
			ptmt.setInt(1, 1);
			ptmt.setString(2, format.format(now) );
			ptmt.setInt(3, lend_id);
			ptmt.execute();
			
	response.sendRedirect("showgoodszujie.html");
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
