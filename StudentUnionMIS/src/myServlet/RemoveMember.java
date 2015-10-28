package myServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myJavaBean.PackingDatabase;

/**
 * Servlet implementation class RemoveMember
 */
@WebServlet("/RemoveMember")
public class RemoveMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveMember() {
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
		request.setCharacterEncoding("utf-8");
		
		String memberId=request.getParameter("memberId");

		String mySql = "update member set tuizhidate=(select CONVERT(varchar(100),getdate(),23)) where member_id='"+memberId+"'";
		PackingDatabase packing = new PackingDatabase();
		try {
			// 执行查询方法
			packing.update(mySql);
		} catch (Exception e) {
			System.out.println("数据库更新异常" + e.getMessage());
		}
		
	}

}
