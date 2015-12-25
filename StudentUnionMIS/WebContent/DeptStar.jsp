<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page
	import="javax.servlet.http.*,java.sql.*,myJavaBean.PackingDatabase,java.io.*"%>
<%@ page import=" java.text.SimpleDateFormat,java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>部门之星</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<h2 class="sub-header" style="padding-left: 1em">部门之星列表</h2>
	<div class="table-responsive">
		<form action="AddDeptStar" method="post" name="myForm">
			<table width="60%" class="table table-striped">
				<tr>
					<td align="center">月份</td>
					<td align="center">姓名</td>
					<td align="center">操作</td>
				</tr>
				<tr>
					<%
						String deptId = (String) request.getSession().getAttribute(
								"departmentId");
						String mySql = "select * from dept_star,member where member.member_id=dept_star.member_id and dept_star.department_id='"
								+ deptId + "' order by dept_star.present_month desc";
						PackingDatabase packing = new PackingDatabase();
						PrintWriter writer = response.getWriter();
						try {
							// 执行查询方法
							ResultSet rs = packing.query(mySql);
							while (rs.next()) {
					%>
					<td align="center"><%=rs.getString("present_month")%>月</td>
					<td align="center"><%=rs.getString("member_name")%></td>
					<td align="center"><a
						href="MemberDetail.html?member_id=<%=rs.getString("member_id")%>">详情</a></td>
				</tr>
				<tr>
					<%
						}
						} catch (Exception e) {
							System.out.println("查询现有部门之星时出现异常：" + e.getMessage());
						}
						Date dt = new Date();
						SimpleDateFormat matter1 = new SimpleDateFormat("MM");
					%>
				</tr>
				<tr>
					<td align="center"><input type="text" name="month"
						value="<%=matter1.format(dt)%>" size="1em" />月</td>
					<td align="center"><select name="member" id="member"
						class="selector">
							<option value="">请选择...</option>
							<%
								String myQuery = "select member_id,member_name from member where member_dpid="
										+ deptId + " and member_roleid='3'";
								String id[] = new String[80];
								String name[] = new String[80];
								int i = 0;
								PackingDatabase pk = new PackingDatabase();
								try {
									// 执行查询方法
									ResultSet rs = pk.query(myQuery);
									while (rs.next()) {
										id[i] = rs.getString("member_id");
										name[i] = rs.getString("member_name");
							%>
							<option value="<%=id[i]%>"><%=name[i]%></option>
							<%
								}
								} catch (Exception e) {
									System.out.println("查询现有成员时出现异常：" + e.getMessage());
								}
							%>
					</select></td>
					<td align="center"><a onclick="myForm.submit()">提交</a></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>