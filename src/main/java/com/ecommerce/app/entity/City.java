package com.ecommerce.app.entity;

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

@Entity
@Table(name = "city")
public class City {
	
	private Long cityId;
	private String cityName;
	private List<Customer> customers;
	private Contry contry;
	
	public City() {
		// TODO Auto-generated constructor stub
	}

	public City(String cityName , List<Customer> customers , Contry contry) {
		super();
		this.cityName = cityName;
		this.customers = customers;
		this.contry = contry;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	@Column(name = "city_name")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@OneToMany(mappedBy = "city" , cascade = {
			CascadeType.DETACH , CascadeType.MERGE , 
			CascadeType.REFRESH , CascadeType.PERSIST
	})
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	@ManyToOne(cascade = {
			CascadeType.DETACH , CascadeType.MERGE,
			CascadeType.PERSIST , CascadeType.REFRESH
	})
	@JoinColumn(name = "contry_id")
	public Contry getContry() {
		return contry;
	}

	public void setContry(Contry contry) {
		this.contry = contry;
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + ", customers=" + customers + ", contry=" + contry
				+ "]";
	}

	
	
}
