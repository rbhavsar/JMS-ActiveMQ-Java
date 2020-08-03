package com.bharath.jms.hm.model;

import java.io.Serializable;

public class Patient implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String insuranceProvider;
	private Double copay;
	private Double amountToBePayed;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInsuranceProvider() {
		return insuranceProvider;
	}

	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}

	public Double getCopay() {
		return copay;
	}

	public void setCopay(Double copay) {
		this.copay = copay;
	}

	public Double getAmountToBePayed() {
		return amountToBePayed;
	}

	public void setAmountToBePayed(Double amountToBePayed) {
		this.amountToBePayed = amountToBePayed;
	}

}
