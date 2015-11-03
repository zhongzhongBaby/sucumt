package myServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myJavaBean.PackingDatabase;

/**
 * Servlet implementation class MeetingCheck
 */
@WebServlet("/MeetingCheck")
public class MeetingCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MeetingCheck() {
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
		
		String dept=request.getParameter("department");
		String meeting_date=request.getParameter("meeting_date");
		String meeting_theme=request.getParameter("meeting_theme");
		String yingdao=request.getParameter("yingdao");
		String shidao=request.getParameter("shidao");
		String qingjia=request.getParameter("qingjia");
		String absent=request.getParameter("absent");
		
		PackingDatabase pd=new PackingDatabase();
		String mySQL="insert into meetingcheck (meeting_date,meeting_department,meeting_theme,yingdao,shidao,absent,qingjia) values('"+meeting_date+"','"+dept+"','"+meeting_theme+"','"+yingdao+"','"+shidao+"','"+absent+"','"+qingjia+"')";
		try {
			// 执行查询方法
			pd.update(mySQL);
		} catch (Exception e) {
			System.out.println("数据库插入异常" + e.getMessage());
		}
	
	}

}
