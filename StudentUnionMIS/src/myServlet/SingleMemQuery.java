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

import org.json.JSONObject;

/**
 * Servlet implementation class SingleMemQuery
 */
@WebServlet("/SingleMemQuery")
public class SingleMemQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleMemQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		String memberId=request.getParameter("memberId");

		String mySql = "select * from member,role where role.role_id=member.member_roleid and member_id='" + memberId + "'";
		PackingDatabase packing = new PackingDatabase();
		try {
			ResultSet rs = packing.query(mySql);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			PrintWriter out = response.getWriter();
			// List<Cart> cart = new ArrayList<Cart>();
			JSONObject jo = new JSONObject();
			while (rs.next()) {
				jo.put("memberId", memberId);
				jo.put("roleName", rs.getString("role_name"));
				jo.put("roleId",rs.getString("role_id"));
				jo.put("memberName", rs.getString("member_name"));
				jo.put("school", rs.getString("xueyuan"));
				jo.put("hometown", rs.getString("jiguan"));
				jo.put("sex", rs.getString("sex"));
				jo.put("birth", rs.getString("birthday"));
				jo.put("hometown", rs.getString("jiguan"));
				jo.put("tel", rs.getString("tel"));
				jo.put("address", rs.getString("address"));
				break;
			}
			rs.close();
			out.print(jo.toString());
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
