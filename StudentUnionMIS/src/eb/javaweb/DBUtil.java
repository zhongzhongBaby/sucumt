package eb.javaweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
	static String url="jdbc:sqlserver://localhost:1433;DatabaseName=XSH_manage";
	static String userName="sa";
	static String password="123456";
	static Connection conn=null;
	
	
	
	public static Connection getConnection(){
		
		try{
			//Ӳ����
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			conn=DriverManager.getConnection(url,userName,password);
		}catch(ClassNotFoundException e){
			
		}		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}