package myServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myJavaBean.PackingDatabase;
import myJavaBean.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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

		// ����һ����ʾ��תҳ�����Ƶı���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// ��ȡ�û��������Ϣ
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String auto = request.getParameter("auto");
			PrintWriter out=response.getWriter();
		String id = "";
		String roleId="";
		String departmentId="";
		String[] authority = new String[30];
		String mySql = "select * from userlist where user_name='" + username
				+ "' and password='" + pwd + "'";
		System.out.println(mySql);
		PackingDatabase select = new PackingDatabase();
		try {
			// ִ�в�ѯ���
			ResultSet rs = select.query(mySql);
			if (rs.next()) {
				id=rs.getString("user_id");
				roleId=rs.getString("role_id");
				departmentId=rs.getString("department_id");
				System.out.println("id="+id);
				System.out.println("roleId="+roleId);
				System.out.println("departmentId="+departmentId);
				
				String myQuery = "select * from roleAuthority where role_id='"+roleId+"'";
				System.out.println(myQuery);
				PackingDatabase pd = new PackingDatabase();
				try {
					// ִ�в�ѯ���
					ResultSet r = pd.query(myQuery);
					int i=0;
					while (r.next()) {
						authority[i]=r.getString("authority_id");
						i++;
					}
					System.out.println(request.getSession().getAttribute("authority"));
					r.close();
				} catch (Exception ee) {
					System.out.println("�û�Ȩ�޶�ȡ�쳣" + ee.getMessage());
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("userId", id);
				session.setAttribute("userName", username);
				session.setAttribute("roleId", roleId);
				session.setAttribute("departmentId", departmentId);
				session.setAttribute("logined", true);
				session.setAttribute("authority", authority);
				if (auto != null) { // ��ѡ��ס���룬����cookie
					Cookie cookie = new Cookie("user", username);
					cookie.setMaxAge(10 * 60);
					response.addCookie(cookie);
					System.out.print("cookie�Ѵ���");
				}
				// request.setAttribute("user", id);
			
			
			response.sendRedirect("web/bumen.html");
			
			
			}else{
				out.print("<script>alert(\"密码不正确\")</script>");
				response.sendRedirect("index.jsp");
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("���ֵ�¼�쳣" + e.getMessage());
		}

		// ����ȡ����Ϣ���浽�û�User
		User user = new User(id, pwd, username,roleId);
		// �����󱣴浽������������
		request.setAttribute("user", user);

	}
}
