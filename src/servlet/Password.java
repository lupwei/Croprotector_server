package servlet;

public class Password {
	
	private String new_password;
	private String phonenumber;
	
	
	public void setNewPassword(String new_password) {
		this.new_password=new_password;
	}
	
	public void setConfirmPassword(String phonenumber) {
		this.phonenumber=phonenumber;
	}
	
	
	public String getNewPassword() {
		return new_password;
	}
	
	public String getPhoneNumber() {
		return phonenumber;
	}
}
