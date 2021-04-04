package com.ecommerce.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
	
	private Long orderDetailId;
	private Integer quantity;
	private Product product;
	private Order order;
	
	public OrderDetail() {
		// TODO Auto-generated constructor stub
	}

	public OrderDetail(Integer quantity , Order order , Product product) {
		super();
		this.quantity = quantity;
		this.order = order;
		this.product = product;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@ManyToOne(cascade = {
			CascadeType.REFRESH , CascadeType.PERSIST,
			CascadeType.MERGE , CascadeType.DETACH
	})
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(cascade = {
			CascadeType.REFRESH , CascadeType.PERSIST,
			CascadeType.MERGE , CascadeType.DETACH
	})
	@JoinColumn(name = "order_id")
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderDetail [quantity=" + quantity + ", product=" + product + ", order=" + order + "]";
	}



}
