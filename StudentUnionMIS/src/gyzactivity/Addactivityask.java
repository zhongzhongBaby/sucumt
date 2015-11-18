package gyzactivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Tool.IpTimeStamp;

/**
 * Servlet implementation class Addactivityask
 */
@MultipartConfig
@WebServlet("/Addactivityask")

public class Addactivityask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addactivityask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String   name=request.getParameter("activityname");
		String   date=request.getParameter("activitydate");
		String   addr=request.getParameter("activityaddr");
		String   chixushijian=request.getParameter("chixushijian");
		String   zerenlaoshi=request.getParameter("zerenlaoshi");
		String   laoshi_tel=request.getParameter("laoshi_tel");
		String   yiyi=request.getParameter("yiyi");
		String   zhubandanwei=request.getParameter("zhubandanwei");
		int jingfeiyusuan=Integer.parseInt(request.getParameter("jingfeiyusuan"));
		PrintWriter out=response.getWriter();
		String path = getPath("/activityfile/");
		System.out.println(path);
		Part part = request.getPart("activityask");
		String filename = getFilename(part);
		writeTo(path, filename, part);
		try {
			Connection conn = eb.javaweb.DBUtil.getConnection();
			PreparedStatement ptmt = conn.prepareStatement("insert into activity (activity_name,state,yiyi,jiaoshi_tel,zerenlaoshi,zhubandanwei,jingfeiyusuan,activity_date,activity_address,filename,laststate) values(?,?,?,?,?,?,?,?,?,?,?)");
			ptmt.setString(1,name);
			ptmt.setInt(2,0);
			ptmt.setString(3,yiyi);
			ptmt.setString(4,laoshi_tel);
			ptmt.setString(5,zerenlaoshi);
			ptmt.setString(6,zhubandanwei);
			ptmt.setInt(7,jingfeiyusuan);
			ptmt.setString(8,date);
			ptmt.setString(9,addr);
			ptmt.setString(10,filename);
			ptmt.setInt(11,0);
			ptmt.execute();
			out.print("申请已经提交,审核结果将在两个工作日内给出，请注意查看!");
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
		doGet(request,response);
		}

	
	private String getFilename(Part part) {
		String header = part.getHeader("Content-Disposition");
		String filename = header.substring(header.indexOf("\"") + 1,
				header.length());
		filename = filename.substring(filename.indexOf("\"") + 1,
				filename.length());
		filename = filename.substring(filename.indexOf("\"") + 1,
				filename.length());
		filename = filename.substring(filename.lastIndexOf("\\")+1);
		filename = filename.substring(0, filename.indexOf("\""));
		return filename;
	}

	
	
	private void writeTo(String path, String filename, Part part)
			throws IOException, FileNotFoundException {
		InputStream in = part.getInputStream();
		OutputStream out = new FileOutputStream(path + filename);
		byte[] buffer = new byte[1024];
		int length = -1;
		while ((length = in.read(buffer)) != -1) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.close();
	}
	
	
	public String getPath(String p){
		return this.getServletContext().getRealPath(p);
	}


	}
