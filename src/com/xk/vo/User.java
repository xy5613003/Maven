package com.xk.vo;



public class User {
	private int userId;
private String username;
private String password;
private String authority;
private String email;
private String phone;
private String userpic;





public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}

public String getUsername() {
	return username;
}
public String getAuthority() {
	return authority;
}
public void setAuthority(String authority) {
	this.authority = authority;
}
public String getUserpic() {
	return userpic;
}
public void setUserpic(String userpic) {
	this.userpic = userpic;
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
