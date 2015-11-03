<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="javax.servlet.http.*,java.sql.*,myJavaBean.PackingDatabase,java.io.*"%>
<%@ page
	import=" java.text.SimpleDateFormat,java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会议考勤</title>
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script type="text/javascript">
	$(document).ready(
			function(){
				$("select[id='department']").change(
						function(){
							/* 由此处开始（jQuery方法获取部门成员列表） */
							$("table tr:gt(5)").remove();
							 var dept_id=$(".selector").val();
							 $.getJSON("MeetingQuery?DeptId="+dept_id, function(jsonData) {
									var html = "<tr>";
									var amount="";
									for (var i = 0; i < jsonData.length; i++) {
											html+="<td align=\"center\">"+ jsonData[i].memberName+"</td>"
											html+="<td align=\"center\"><input type=\"radio\" id=\"Y\" name=\"check"+i+"\" value=\"Y\"></td>";
											html+="<td align=\"center\"><input type=\"radio\" id=\"Q\" name=\"check"+i+"\" value=\"Q\"></td>";
											html+="<td align=\"center\"><input type=\"radio\" id=\"A\" name=\"check"+i+"\" value=\"A\"></td>";
										if(i%2==1){
											html+="</tr><tr>"
										}
										if(i==jsonData.length-1){
											amount+=jsonData[i].yingdao;
										}
									}
									html+="</tr>";
									$("tr[id='deptMember']").after(html);
									$("input[name='yingdao']").val(amount);
							});   
						});
			});		
</script>
<script type="text/javascript">
	function myFunction(){
		var i=$("input[id='Y']:checked").length;
		var j=$("input[id='Q']:checked").length;
		var p=$("input[id='A']:checked").length;
		$("input[name='shidao']").val(i);
		$("input[name='qingjia']").val(j);
		$("input[name='absent']").val(p); 
	}
	
	function aa(){
		if(myForm.shidao.value==""||myForm.qingjia.value==""||myForm.absent.value==""){
			alert("请先统计人数");
		} else{
			myForm.submit();
		}
	}
</script>
</head>
<body>
	<center>
		<h3>会议考勤</h3>
	</center>
	<form action="MeetingCheck" method="post" name="myForm">
		<table align="center" width=“80%” border="1">
			<tr>
				<td align="right">开会部门：</td>
				<td align="left" colspan="3"><select name="department" id="department" class="selector">
					<option value="">请选择...</option>
						<%
							String mySql = "select * from department";
							String dept[] = new String[20];
							String deptId[] = new String[20];
							int i = 0;
							PackingDatabase packing = new PackingDatabase();
							PrintWriter writer = response.getWriter();
							try {
								// 执行查询方法
								ResultSet rs = packing.query(mySql);
								while (rs.next()) {
									dept[i] = rs.getString("department_name");
									deptId[i] = rs.getString("department_id");
						%>
						<option value="<%=deptId[i]%>"><%=dept[i]%></option>
						<%
							}
							} catch (Exception e) {
								System.out.println("查询现有部门时出现异常：" + e.getMessage());
							}
							Date dt=new Date();
						    SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
						     System.out.println(matter1.format(dt)); 
						%>
				</select></td>
				<td align="right">会议日期：</td>
				<td align="left" colspan="3">
					<input type="text" name="meeting_date" value="<%=matter1.format(dt) %>" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td align="right">会议主题：</td>
				<td align="left" colspan="7">
					<input type="text" name="meeting_theme" size="66"/>
				</td>
			</tr>
			<tr>
				<td align="right">应到人数：</td>
				<td align="left" colspan="3">
					<input type="text" name="yingdao" value="" />
				</td>
				<td align="right">实到人数：</td>
				<td align="left" colspan="3">
					<input type="text" name="shidao" />
				</td>
			</tr>
			<tr>
				<td align="right">请假人数：</td>
				<td align="left" colspan="3">
					<input type="text" name="qingjia" />
				</td>
				<td align="right">缺勤人数：</td>
				<td align="left" colspan="3">
					<input type="text" name="absent" />
				</td>
			</tr>
			<tr>
				<td colspan="8" align="center">点名</td>
			</tr>
			<tr id="deptMember">
				<td align="center">姓名</td>
				<td align="center">与会</td>
				<td align="center">请假</td>
				<td align="center">缺勤</td>
				<td align="center">姓名</td>
				<td align="center">与会</td>
				<td align="center">请假</td>
				<td align="center">缺勤</td>
			</tr>
		</table>
		<center>
			<input type="button" onclick="myFunction()" value="统计人数" />&emsp;&emsp;&emsp;&emsp;<input type="button" onclick="aa()" value="确认提交" />&emsp;&emsp;&emsp;&emsp;<input type="reset" value="重置" />
		</center>
	</form>

</body>
</html>