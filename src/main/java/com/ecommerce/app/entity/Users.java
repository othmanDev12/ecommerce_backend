package com.ecommerce.app.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;



@Entity
@Table(name = "users")
public class Users {
	private Long userId;
	private String email;
	private String password;
	private String fullname;
	private byte[] image;
	
	public Users() {
		// TODO Auto-generated constructor stub
	}

	public Users(String email, String password, String fullname, byte[] image) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.image = image;
	}

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "full_name")
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "image")
	@Lob
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", email=" + email + ", password=" + password + ", fullname=" + fullname
				+    ", image=" + Arrays.toString(image) + "]";
	}
	
	

}
