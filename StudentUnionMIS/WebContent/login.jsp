<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="javax.servlet.http.*,java.sql.*,myJavaBean.PackingDatabase"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<title>登录</title>
</head>
<body>
	<%
		String username = "";
		String password = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();

				System.out.println(value);
				String mySql = "select user_name,password from userlist where user_name='"
						+ value + "'";
				System.out.println(mySql);
				PackingDatabase select = new PackingDatabase();
				try {
					// 执行查询语句
					ResultSet rs = select.query(mySql);
					if (rs.next()) {
						username = value;
						password = rs.getString("password");
						break;
					}
					rs.close();
				} catch (Exception e) {
					System.out.println("SQL查询异常" + e.getMessage());
				}
			}
		}
	%>
	<div class="table-responsive">
		<form action="LoginServlet" method="post" name="myForm">
			<table align="center" class="table table-striped">
				<tbody>
					<tr>
						<td align="center" valign="middle" colspan="2"><h3>用户登录</h3></td>
					</tr>
					<tr>
						<td align="right" width="50%">账号：<input type="text"
							name="username" size="19em" value="<%=username%>" /></td>
						<td align="left"><a href="">注册</a></td>
					</tr>
					<tr>
						<td align="right">密码：<input type="password" name="pwd"
							size="19em" value="<%=password%>" /></td>
						<td align="left"><a href="">忘记密码？</a></td>
					</tr>
					<tr>
						<td align="right"><input type="checkbox" name="auto"></td>
						<td align="left">记住密码</td>
					</tr>
					<tr>
						<td align="center" colspan="2"><p>
								<input type="submit" value="登录" class="btn btn-primary" />&nbsp;&nbsp;&nbsp;&nbsp;<input
									type="reset" value="取消" class="btn btn-primary" />
							</p></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>