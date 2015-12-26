package gyzactivity;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Tool.IpTimeStamp;

/**
 * Servlet implementation class Addgoodszujie
 */
@WebServlet("/Addgoodszujie")
public class Addgoodszujie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addgoodszujie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String lend_date=request.getParameter("lend_date");
		String return_date= request.getParameter("guihuan_date");
		String detail= request.getParameter("detail");
		String Lend_danwei= request.getParameter("danwei");
		Double money=Double.parseDouble(request.getParameter("lend_money"));
		Date date=new Date();
		date=Strtodate(lend_date);
		System.out.print(date);
		
		
		
		
	try {
			Connection conn = eb.javaweb.DBUtil.getConnection();
			PreparedStatement ptmt = conn.prepareStatement("insert into lendgoods (detail,lend_date,return_date,state,lend_danwei,lend_money) values(?,?,?,?,?,?)");
			ptmt.setString(1, detail);
			ptmt.setString(2, lend_date);
			ptmt.setString(3, lend_date);
			ptmt.setInt(4, 0);
			ptmt.setString(5,Lend_danwei);
			ptmt.setDouble(6,money);
			ptmt.execute();
			out.print("申请已经提交");
		}

		catch (SQLException e) {
			out.print(e);
		}
	}

	
	
	public static Date Strtodate(String str){
		SimpleDateFormat format =new SimpleDateFormat("yyyy-mm-dd");
		Date date=null;
		try {
			date=format.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}



}
