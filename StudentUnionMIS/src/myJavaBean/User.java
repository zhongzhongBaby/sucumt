package myJavaBean;

public class User {
	private String id;
	private String pwd1;
	private String name;
	private String roleId;
	
	public User(){
	}
	
	public User(String id,String pwd1,String name,String roleId){
		this.id=id;
		this.pwd1=pwd1;
		this.name=name;

	}
		
	public void setId(String id){
		this.id=id;
	}
	
	public String getId(){
		return id;
	}
	
	public void setPwd1(String pwd1){
		this.pwd1=pwd1;
	}
	
	public String getPwd1(){
		return pwd1;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
