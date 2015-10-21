package myServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myJavaBean.PackingDatabase;

/**
 * Servlet implementation class AddMember
 */
@WebServlet("/AddMember")
public class AddMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		String memName = request.getParameter("memName");
		String school = request.getParameter("school");
		String role = request.getParameter("role");
		String hometown = request.getParameter("hometown");
		String sex = request.getParameter("sex");
		String birth = request.getParameter("birth");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");

		String deptId = (String) request.getSession().getAttribute(
				"departmentId");

		String mySql = "insert into member (member_name,member_roleid,member_dpid,xueyuan,jiguan,sex,birthday,tel,address) values('"
				+ memName
				+ "','"
				+ role
				+ "','"
				+ deptId
				+ "','"
				+ school
				+ "','"
				+ hometown
				+ "','"
				+ sex
				+ "','"
				+ birth
				+ "','"
				+ tel
				+ "','" + address + "')";
		PackingDatabase packing = new PackingDatabase();
		try {
			// 执行查询方法
			packing.update(mySql);
		} catch (Exception e) {
			System.out.println("数据库插入异常" + e.getMessage());
		}

	}

}
