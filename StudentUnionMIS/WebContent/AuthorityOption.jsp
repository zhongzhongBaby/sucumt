<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page
	import="javax.servlet.http.*,java.sql.*,myJavaBean.PackingDatabase,java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户权限设置</title>
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script type="text/javascript">
$(document).ready(
		function(){
			 $("select[id='role']").change(
					function(){
						 var roleId=$(".selector").val(); 
						 $("form[name='myForm']").attr("action","AuthorityOption?roleId="+roleId);
						 $("input[type='checkbox']").removeAttr("checked"); 
						 $.getJSON("AuthorityQuery?roleId="+roleId, function(jsonData) {
							for (var i = 0; i < jsonData.length; i++){
									$("input[value='"+jsonData[i].authorityId+"']").prop('checked',"checked");	
							}
						}); 
					}); 
		});	
</script>
</head>
<body>
	<center>
		<h3>用户权限设置</h3>
	</center>
	<form action="AuthorityOption" method="post" name="myForm">
		<table align="center" border="1">
			<tr>
				<td>用户角色：</td>
				<td><select name="role" id="role" class="selector">
						<option value="">请选择...</option>
						<%
							String mySql = "select * from role where role_name!='管理员'";
							String role[] = new String[20];
							String roleId[] = new String[20];
							int i = 0;
							PackingDatabase packing = new PackingDatabase();
							PrintWriter writer = response.getWriter();
							try {
								// 执行查询方法
								ResultSet rs = packing.query(mySql);
								while (rs.next()) {
									role[i] = rs.getString("role_name");
									roleId[i] = rs.getString("role_id");
						%>
						<option value="<%=roleId[i]%>"><%=role[i]%></option>
						<%
							}
							} catch (Exception e) {
								System.out.println("查询用户角色时出现异常：" + e.getMessage());
							}
						%>
				</select></td>
			</tr>
			<%
							String query1 = "select distinct authority_class,class_eng from authority ";
							PackingDatabase pd1 = new PackingDatabase();
							String AuthorityClass;
							try {
								// 执行查询方法
								ResultSet rs = pd1.query(query1);
								while (rs.next()) {
									AuthorityClass=rs.getString("authority_class");
			%>
			<tr>
				<td colspan="2"><%=AuthorityClass %>：</td>
			</tr>
			<%
									String query2 = "select distinct authority_detail,authority_id from authority where authority_class='"+AuthorityClass+"'";				
									PackingDatabase pd2 = new PackingDatabase();
									try {
										// 执行查询方法
										ResultSet r = pd2.query(query2);
										while (r.next()) {
			%>
			<tr>
				<td></td>
				<td><input type="checkbox"
					name="<%=rs.getString("class_eng") %>"
					value="<%=r.getString("authority_id")%>">&nbsp;<%=r.getString("authority_detail")%>
				</td>
			</tr>
			<%	
										}
									} catch (Exception e) {
										System.out.println("查询权限详情时出现异常：" + e.getMessage());
									}			
								}
							} catch (Exception e) {
								System.out.println("查询权限目录时出现异常：" + e.getMessage());
							}
						%>
		</table>
		<center>
			<p>
				<input type="submit" value="提交" />&emsp;&emsp;<input type="reset"
					value="重置" />
			</p>
		</center>
	</form>
</body>
</html>