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
 * Servlet implementation class Addbill
 */
@MultipartConfig
@WebServlet("/Addbill")
public class Addbill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addbill() {
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
		PrintWriter out=response.getWriter();
		Tool.IpTimeStamp buf = new IpTimeStamp ();
		String ip=buf.getIPTimeRand();	
		System.out.print(ip);
		String path = getPath("/file/");
		Part part = request.getPart("baobiao");
		String filename = getFilename(part);
		writeTo(path,ip+filename, part);
		part.write(ip+filename);
		Integer activityid=Integer.parseInt(request.getParameter("activityid"));
		Integer zanzhu=Integer.parseInt(request.getParameter("zanzhu"));
		Integer zongzhichu=Integer.parseInt(request.getParameter("zongzhichu"));
		try {
			Connection conn = eb.javaweb.DBUtil.getConnection();
			PreparedStatement ptmt = conn.prepareStatement("insert into bill (activity_id,bill_pay,bill_zanzhu,baobiaoname) values(?,?,?,?)");
			ptmt.setInt(1, activityid );
			ptmt.setDouble(2,zongzhichu);
			ptmt.setDouble(3,zanzhu);
			ptmt.setString(4,ip+filename );
						ptmt.execute();
			out.print("报表已经提交！");
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		Tool.IpTimeStamp buf = new IpTimeStamp ();
		String ip=buf.getIPTimeRand();	
		System.out.print(ip);
		String path = getPath("/file/");
		Part part = request.getPart("baobiao");
		String filename = getFilename(part);
		writeTo(path,ip+filename, part);
		part.write(ip+filename);
		Integer activityid=Integer.parseInt(request.getParameter("activityid"));
		Integer zanzhu=Integer.parseInt(request.getParameter("zanzhu"));
		Integer zongzhichu=Integer.parseInt(request.getParameter("zongzhichu"));
		try {
			Connection conn = eb.javaweb.DBUtil.getConnection();
			PreparedStatement ptmt = conn.prepareStatement("insert into bill (activity_id,bill_pay,bill_zanzhu,baobiaoname) values(?,?,?,?)");
			ptmt.setInt(1, activityid );
			ptmt.setDouble(2,zongzhichu);
			ptmt.setDouble(3,zanzhu);
			ptmt.setString(4,ip+filename );
						ptmt.execute();
			out.print("报表已经提交！");
		}

		catch (SQLException e) {
			out.print(e);
		}

				
		
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