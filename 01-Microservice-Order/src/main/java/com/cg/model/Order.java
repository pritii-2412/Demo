package com.cg.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="MSorder_table")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="o_id")
	private int oid;
	
	@NotNull(message = "Order date cannot be null")
	@Column(name="o_date")
	private LocalDate odate;
	
	 @Min(value = 1, message = "Order number must be greater than 0")
	@Column(name="o_no")
	private int orderno;
	 

	public Order(int oid, LocalDate odate, int orderno) {
		super();
		this.oid = oid;
		this.odate = odate;
		this.orderno = orderno;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public LocalDate getOdate() {
		return odate;
	}

	public void setOdate(LocalDate odate) {
		this.odate = odate;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", odate=" + odate + ", orderno=" + orderno + "]";
	}

	
	
	

}