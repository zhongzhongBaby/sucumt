package myServlet;

import java.io.IOException;
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

		// 定义一个表示跳转页面名称的变量
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取用户输入的信息
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String auto = request.getParameter("auto");

		String id = "";
		String roleId="";
		String departmentId="";
		String[] authority = new String[30];
		String mySql = "select * from userlist where user_name='" + username
				+ "' and password='" + pwd + "'";
		System.out.println(mySql);
		PackingDatabase select = new PackingDatabase();
		try {
			// 执行查询语句
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
					// 执行查询语句
					ResultSet r = pd.query(myQuery);
					int i=0;
					while (r.next()) {
						authority[i]=r.getString("authority_id");
						i++;
					}
					System.out.println(request.getSession().getAttribute("authority"));
					r.close();
				} catch (Exception ee) {
					System.out.println("用户权限读取异常" + ee.getMessage());
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("userId", id);
				session.setAttribute("userName", username);
				session.setAttribute("roleId", roleId);
				session.setAttribute("departmentId", departmentId);
				session.setAttribute("logined", true);
				session.setAttribute("authority", authority);
				
				if (auto != null) { // 勾选记住密码，保存cookie
					Cookie cookie = new Cookie("user", username);
					cookie.setMaxAge(10 * 60);
					response.addCookie(cookie);
					System.out.print("cookie已创建");
				}
				// request.setAttribute("user", id);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("出现登录异常" + e.getMessage());
		}

		// 将获取的信息保存到用户User
		User user = new User(id, pwd, username,roleId);
		// 将对象保存到请求上下文中
		request.setAttribute("user", user);

	}
}
