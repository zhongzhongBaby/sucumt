package gyzactivity;

import java.io.IOException;
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
 * Servlet implementation class Addrepartment
 */
@MultipartConfig(location = "G:\\")
@WebServlet("/Addrepartment")

public class Addrepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addrepartment() {
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
		Tool.IpTimeStamp buf = new IpTimeStamp ();
		String ip=buf.getIPTimeRand();	
		System.out.print(ip);
		String department_name= request.getParameter("department_name");
		Integer department_renshu=Integer.parseInt(request.getParameter("department_renshu")) ;
		String department_zhineng= request.getParameter("department_zhineng");
		/*System.out.println(path);*/
		Part part = request.getPart("cailiao");
		String filename = getFilename(part);
		/*writeTo(path, filename, part);*/
		part.write(ip+filename);
		out.println(filename);
		try {
			Connection conn = eb.javaweb.DBUtil.getConnection();
			PreparedStatement ptmt = conn.prepareStatement("insert into department (department_name,department_renshu,department_zhineng,state,shenqingcailiao) values(?,?,?,?,?)");
			ptmt.setString(1,department_name);
			ptmt.setInt(2,  department_renshu);
			ptmt.setString(3, department_zhineng );
			ptmt.setInt(4,0);
			ptmt.setString(5,ip+filename);
			ptmt.execute();
			out.print("部门增加申请已经提交");
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
}
