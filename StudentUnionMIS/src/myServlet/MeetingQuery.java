package myServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myJavaBean.PackingDatabase;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class MeetingQuery
 */
@WebServlet("/MeetingQuery")
public class MeetingQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MeetingQuery() {
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
		request.setCharacterEncoding("utf-8");
		
		String DeptId = request.getParameter("DeptId");
		System.out.println(DeptId);
		int i = 0;
		String mySql = "select member_name,tuizhidate from member where member_dpid='"
				+ DeptId + "'";
		System.out.println(mySql);
		PackingDatabase packing = new PackingDatabase();
		try {
			// 执行查询方法
			ResultSet rs = packing.query(mySql);
			response.setContentType("text/x-json;charset=utf-8");
			PrintWriter out = response.getWriter();
			JSONArray ja = new JSONArray();
			while (rs.next()) {
				if(rs.getString("tuizhidate") == null){
					JSONObject jo = new JSONObject();
					i++;
					jo.put("memberName", rs.getString("member_name"));
					jo.put("yingdao", i);
					ja.put(jo);
				}
				
			}
			rs.close();
			out.print(ja.toString());
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
