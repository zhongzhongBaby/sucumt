<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="javax.servlet.http.*,java.sql.*,myJavaBean.PackingDatabase"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>学生会办公室管理系统</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<script src="js/jquery.min.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="js/bootstrap.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<style>
tr>td {
	font-size: 1.5em;
}

.login {
	color: #fff;
	background-color: #d1c792;
	border-color: #d1c792;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
	-webkit-user-select: none;
	display: inline-block;
	padding: 6px 12px;
	margin-bottom: 0;
	font-size: 14px;
	font-weight: 400;
	line-height: 1.42857143;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
}
</style>
</head>
<body>
	<div class="header">
		<div class="header-top">
			<div class="container" style="margin-right: 2em">
				<div class="header-top-top" style="padding: 0">
					<div class=" header-top-right" style="width: auto">
						<a href="#" id="login"
							style="color: #fff; font-weight: 400; text-align: right;">您好，请登录</a>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="banner">
		<div class="slider">
			<div class="container">
				<div class="callbacks_container col-lg-6 col-md-6 col-sm-6">
					<ul class="rslides" id="slider">
						<li>
							<h3>学生会办公室管理系统</h3>
						</li>
					</ul>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6">
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
							<table align="center" class="table">
								<tbody>
									<tr>
										<td align="right" valign="middle"><h2
												style="color: white;">用户登录</h2></td>
										<td></td>
									</tr>
									<tr>
										<td align="right"><font color="white">账号：</font>&emsp;<input
											id="username" type="text" name="username" size="19em"
											value="<%=username%>" /></td>
										<td align="left">&emsp;<a href="" style="color: white;">立即注册</a></td>
									</tr>
									<tr>
										<td align="right"><font color="white">密码：</font>&emsp;<input
											type="password" name="pwd" size="19em" value="<%=password%>" /></td>
										<td align="left">&emsp;<a href="" style="color: white;">忘记密码</a></td>
									</tr>
									<tr>
										<td align="right" style="color: white;"><input
											type="checkbox" name="auto">&emsp;记住密码</td>
										<td></td>
									</tr>
									<tr>
										<td align="right" style="color: white;"><p>
												<input type="submit" value="登录" class="login" />
											</p></td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="banner-bottom">
		<div class="b-grid1" style="height: auto">
			<a href="#" style="margin-top: 0">部门管理</a>
		</div>
		<div class="b-grid2" style="height: auto">
			<a href="#" style="margin-top: 0">人员管理</a>
		</div>
		<div class="b-grid3" style="height: auto">
			<a href="#" style="margin-top: 0">活动管理</a>
		</div>
		<div class="b-grid4" style="height: auto">
			<a href="#" style="margin-top: 0">物品管理</a>
		</div>
		<div class="b-grid5" style="height: auto">
			<a href="#" style="margin-top: 0">权限管理</a>
		</div>
		<div class="clearfix" style="height: auto"></div>
	</div>


	<script>
		$("#login").click(function() {
			$("#username").focus();
		})
	</script>
</body>
</html>