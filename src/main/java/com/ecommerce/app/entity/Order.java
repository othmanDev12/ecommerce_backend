package com.ecommerce.app.entity;

import java.util.Date;
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

import com.ecommerce.app.enumType.MyorderStatus;
import com.ecommerce.app.enumType.MypaymentMethod;
import com.ecommerce.app.enumType.MypaymentStatus;

@Entity
@Table(name = "order")
public class Order {
	
	private Long orderId;
	private Date orderDate;
	private MyorderStatus orderStatus;
	private MypaymentStatus paymentStatus;
	private MypaymentMethod paymentMethod;
	private String addressShipping;
	private String recipientFullName;
	private String recipientPhone;
	private Customer customer;
	private List<OrderDetail> orderDetails;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(Date orderDate, MyorderStatus orderStatus, MypaymentStatus paymentStatus,
			MypaymentMethod paymentMethod, String addressShipping, String recipientFullName, String recipientPhone
			, Customer customer , List<OrderDetail> orderDetails) {
		super();
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
		this.addressShipping = addressShipping;
		this.recipientFullName = recipientFullName;
		this.recipientPhone = recipientPhone;
		this.customer = customer;
		this.orderDetails = orderDetails;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Column(name = "order_date")
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "order_status")
	public MyorderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(MyorderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Column(name = "payment_status")
	public MypaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(MypaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Column(name = "payment_method")
	public MypaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(MypaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "address_shipping")
	public String getAddressShipping() {
		return addressShipping;
	}

	public void setAddressShipping(String addressShipping) {
		this.addressShipping = addressShipping;
	}

	@Column(name = "recipient_full_name")
	public String getRecipientFullName() {
		return recipientFullName;
	}

	public void setRecipientFullName(String recipientFullName) {
		this.recipientFullName = recipientFullName;
	}

	@Column(name = "recipient_phone")
	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}
	
	@ManyToOne(cascade = {
			CascadeType.DETACH ,  CascadeType.MERGE,
			CascadeType.PERSIST , CascadeType.REFRESH
	})
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@OneToMany(mappedBy = "order" , cascade = {
			CascadeType.MERGE , CascadeType.PERSIST,
			CascadeType.DETACH , CascadeType.REFRESH
	})
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus
				+ ", paymentStatus=" + paymentStatus + ", paymentMethod=" + paymentMethod + ", addressShipping="
				+ addressShipping + ", recipientFullName=" + recipientFullName + ", recipientPhone=" + recipientPhone
				+ ", customer=" + customer + ", orderDetails=" + orderDetails + "]";
	}

}
