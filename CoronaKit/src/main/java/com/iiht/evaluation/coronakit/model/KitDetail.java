package com.iiht.evaluation.coronakit.model;

public class KitDetail {

	private int id;
	private int coronaKitId;
	private int productId;
	private int quantity;
	private Double amount;
	private String productName;
	
	public KitDetail() {
		// TODO Auto-generated constructor stub
	}
	
	public KitDetail(int id, int coronaKitId, int productId, String productName, int quantity, Double amount) {
		this.id = id;
		this.coronaKitId = coronaKitId;
		this.productId = productId;
		this.productName=productName;
		this.quantity = quantity;
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCoronaKitId() {
		return coronaKitId;
	}
	public void setCoronaKitId(int coronaKitId) {
		this.coronaKitId = coronaKitId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
