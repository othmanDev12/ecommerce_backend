package com.ecommerce.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "contry")
public class Contry {
	
	private Long contryId;
	private String contryName;
	private List<City> cities;
	
	public Contry() {
		// TODO Auto-generated constructor stub
	}

	public Contry(String contryName , List<City> cities) {
		super();
		this.contryName = contryName;
		this.cities = cities;
	}

	@Id
	@Column(name = "contry_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getContryId() {
		return contryId;
	}

	public void setContryId(Long contryId) {
		this.contryId = contryId;
	}

	@Column(name = "contry_name")
	public String getContryName() {
		return contryName;
	}

	public void setContryName(String contryName) {
		this.contryName = contryName;
	}
	
	@OneToMany(mappedBy = "contry" , cascade = {
			CascadeType.PERSIST , CascadeType.MERGE,
			CascadeType.REFRESH , CascadeType.DETACH
	})
	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		return "Contry [contryId=" + contryId + ", contryName=" + contryName + ", cities=" + cities + "]";
	}

	

}
