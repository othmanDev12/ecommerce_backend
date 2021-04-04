package com.ecommerce.app.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.ecommerce.app.enumType.MyBrand;
import com.ecommerce.app.enumType.Mygeneration;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
public class Product {
	
	private Long productId;
	private String productName;
	private MyBrand brand;
	private String model;
	private String cpu;
	private float price;
	private Date releaseDate;
	private String ram;
	private String description;
	private byte[] image;
	private Mygeneration generation;
	private Category category;
	private Promotion promotion;
	private List<OrderDetail> orderDetails;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String productName, MyBrand brand, String model, String cpu, float price, Date releaseDate,
			String ram, String description, byte[] image, Mygeneration generation , Category category,
			List<OrderDetail> orderDetails) {
		super();
		this.productName = productName;
		this.brand = brand;
		this.model = model;
		this.cpu = cpu;
		this.price = price;
		this.releaseDate = releaseDate;
		this.ram = ram;
		this.description = description;
		this.image = image;
		this.generation = generation;
		this.category = category;
		this.orderDetails = orderDetails;
	}

	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "product_name")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "brand")
	@Enumerated(EnumType.STRING)
	public MyBrand getBrand() {
		return brand;
	}

	public void setBrand(MyBrand brand) {
		this.brand = brand;
	}

	@Column(name = "model")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "cpu")
	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	@Column(name = "price")
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Column(name = "release_date")
	@JsonFormat(pattern = "mm/dd/yyyy")
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Column(name = "ram")
	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "image")
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Column(name = "generation")
	@Enumerated(EnumType.STRING)
	public Mygeneration getGeneration() {
		return generation;
	}

	public void setGeneration(Mygeneration generation) {
		this.generation = generation;
	}
	
	@ManyToOne(cascade = {CascadeType.DETACH , CascadeType.PERSIST
		, CascadeType.MERGE , CascadeType.REFRESH})
	@JoinColumn(name = "category_id")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne(cascade = {
			CascadeType.PERSIST , CascadeType.MERGE,
			CascadeType.DETACH , CascadeType.REFRESH
	})
	@JoinColumn(name = "promotion_id")
	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	@OneToMany(mappedBy = "product" , cascade = {
			CascadeType.DETACH , CascadeType.REFRESH,
			CascadeType.MERGE , CascadeType.PERSIST
	})
	@JsonIgnore
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", brand=" + brand + ", model="
				+ model + ", cpu=" + cpu + ", price=" + price + ", releaseDate=" + releaseDate + ", ram=" + ram
				+ ", description=" + description + ", image=" + Arrays.toString(image) + ", generation=" + generation
				+ ", category=" + category + ", promotion=" + promotion + ", orderDetails=" + orderDetails + "]";
	}
	
}
