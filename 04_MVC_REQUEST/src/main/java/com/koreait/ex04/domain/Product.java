package com.koreait.ex04.domain;

public class Product {

	//field
	private String modelName;
	private int price;
	
	//getter
	public String getModelName() {
		return modelName;
	}
	public int getPrice() {
		return price;
	}
	//constructor
	private Product(Builder builder) {
		this.modelName = builder.modelName;
		this.price = builder.price;
	}
	//Builder내부 클래스
	public static class Builder{
		private String modelName;
		private int price;
		
		//setter
		public Builder setModelName(String modelName) {
			this.modelName = modelName;
			return this;
		}
		public Builder setPrice(int price) {
			this.price = price;
			return this;
		}
		public Builder() {
			
		}//build():Product를 반환
		public Product build() {
			return new Product(this);
		}
		
	}
	
}
