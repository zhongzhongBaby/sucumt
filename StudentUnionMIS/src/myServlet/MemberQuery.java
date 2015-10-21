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
 * Servlet implementation class MemberQuery
 */
@WebServlet("/MemberQuery")
public class MemberQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		String departmentId = (String) request.getSession().getAttribute("departmentId");
		String mySql = "select * from member,role where role.role_id=member.member_roleid and member_dpid='" + departmentId + "'";
		PackingDatabase packing = new PackingDatabase();
		try {
			// 执行查询方法
			ResultSet rs = packing.query(mySql);
			response.setContentType("text/x-json;charset=utf-8");

			PrintWriter out = response.getWriter();
			JSONArray ja = new JSONArray();
			while (rs.next()) {
				JSONObject jo = new JSONObject();
				jo.put("roleName", rs.getString("role_name"));
				jo.put("memberName", rs.getString("member_name"));
				jo.put("memberId", rs.getString("member_id"));
				jo.put("school", rs.getString("xueyuan"));
				jo.put("hometown", rs.getString("jiguan"));
				jo.put("sex", rs.getString("sex"));
				jo.put("birth", rs.getString("birthday"));
				jo.put("hometown", rs.getString("jiguan"));
				jo.put("tel", rs.getString("tel"));
				jo.put("address", rs.getString("address"));
				ja.put(jo);
			}
			rs.close();
			out.print(ja.toString());
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
