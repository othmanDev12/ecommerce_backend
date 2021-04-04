package com.ecommerce.app.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.ecommerce.app.enumType.MyprmotionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "promotion")
public class Promotion {
	
	private Long promotionId;
	private String promotionName;
	private MyprmotionType promotionType;
	private Date beginDate;
	private Date endDate;
	private float value;
	private List<Product> products;
	
	public Promotion() {
		// TODO Auto-generated constructor stub
	}

	public Promotion(String promotionName, MyprmotionType promotionType, Date beginDate, Date endDate,
			List<Product> products) {
		super();
		this.promotionName = promotionName;
		this.promotionType = promotionType;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.products = products;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "promotion_id")
	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	@Column(name = "promotion_name")
	public String getPromotionName() {
		return promotionName;
	}

	@Column(name = "value")
	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	@Column(name = "promotion_type")
	@Enumerated(EnumType.STRING)
	public MyprmotionType getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(MyprmotionType promotionType) {
		this.promotionType = promotionType;
	}

	@Column(name = "begin_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getBeginDate() {
		return beginDate;
	}


	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@Column(name = "end_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@OneToMany(mappedBy = "promotion" , cascade =  {
			CascadeType.DETACH , CascadeType.MERGE,
			CascadeType.REFRESH , CascadeType.PERSIST
	})

	@JsonIgnore
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Promotion [promotionId=" + promotionId + ", promotionName=" + promotionName + ", promotionType="
				+ promotionType + ", beginDate=" + beginDate + ", endDate=" + endDate + ", products=" + products + "]";
	}
	
	
}
