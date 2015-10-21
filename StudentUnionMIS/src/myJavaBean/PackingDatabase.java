package myJavaBean;

import java.sql.*;

public class PackingDatabase {
	private String DBDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://localhost:1433; DatabaseName=XSH_manage";
	private String username = "sa";
	private String password = "123456";
	public Connection conn = null;
	public ResultSet rs = null;

	public ResultSet query(String mySql) throws Exception {
		try {
			Class.forName(DBDriver);
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(mySql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void update(String mySql) throws Exception {
		try {
			Class.forName(DBDriver);
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(mySql);
			stmt.close();
			conn.close();
		} catch (SQLException ee) {
			// TODO Auto-generated catch block
			System.out.println(ee.getMessage());
		}
	}

	public void setDBDriver(String DBDriver) {
		this.DBDriver = DBDriver;
	}

	public String getDBDriver() {
		return DBDriver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
