package myServlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myJavaBean.PackingDatabase;

/**
 * Servlet implementation class AuthorityOption
 */
@WebServlet("/AuthorityOption")
public class AuthorityOption extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorityOption() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String roleId=request.getParameter("roleId");
		
		String mySql = "delete roleAuthority where role_id='"+roleId+"'";
		PackingDatabase packing = new PackingDatabase();
		try {
			packing.update(mySql);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	
		
		
		String query = "select distinct class_eng from authority";
		PackingDatabase packing1 = new PackingDatabase();
		try {
			// 执行查询方法
			ResultSet rs = packing1.query(query);
			while (rs.next()) {
				String class_eng=rs.getString("class_eng");
				/*System.out.println(class_eng);*/
				/*读取上下文中的数据并写入数组*/
				if(request.getParameterValues(class_eng)!=null){
					String[] authority = request.getParameterValues(class_eng);
					/*System.out.println("jadhjkdah"+authority.length);*/
					for(int i=0;i<authority.length;i++){
						System.out.println(authority[i]);
						String query2="insert into roleAuthority (role_id,authority_id) values('"+roleId+"','"+authority[i]+"')";
						PackingDatabase packing2=new PackingDatabase();
						try {
							packing2.update(query2);
						}catch (Exception ee) {
							System.out.println(ee.getMessage());
						}
					}		
				}
			}
		} catch (Exception e) {
			System.out.println("查询时出现了异常：" + e.getMessage());
		}
	}

}
