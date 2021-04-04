package com.ecommerce.app.entity;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ecommerce.app.enumType.Mygender;

@Entity
@Table(name = "customer")
public class Customer {
	
	private Long customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Mygender mygender;
	private String phone;
	private String address;
	private String zipcode;
	private byte[] image;
	private City city;
	private List<Order> orders;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String firstName, String lastName, String email, String password, Mygender mygender, String phone,
			String address, String zipcode, byte[] image , City city , List<Order> orders) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mygender = mygender;
		this.phone = phone;
		this.address = address;
		this.zipcode = zipcode;
		this.image = image;
		this.city = city;
		this.orders = orders;
	}

	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	@Column(name = "gender")
	public Mygender getMygender() {
		return mygender;
	}

	public void setMygender(Mygender mygender) {
		this.mygender = mygender;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "zipcode")
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "image")
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	@ManyToOne(cascade = {
			CascadeType.DETACH ,  CascadeType.MERGE,
			CascadeType.PERSIST , CascadeType.REFRESH
	})
	@JoinColumn(name = "city_id")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	@OneToMany(mappedBy = "customer" , cascade = {
			CascadeType.DETACH ,  CascadeType.MERGE,
			CascadeType.PERSIST , CascadeType.REFRESH
	})
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + ", mygender=" + mygender + ", phone=" + phone + ", address="
				+ address + ", zipcode=" + zipcode + ", image=" + Arrays.toString(image) + ", city=" + city
				+ ", orders=" + orders + "]";
	}


}
