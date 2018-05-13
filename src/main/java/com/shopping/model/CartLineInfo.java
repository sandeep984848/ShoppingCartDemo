package com.shopping.model;

public class CartLineInfo {
	
	private ProductDetails productDetails;
    private int quantity;
	 
    public ProductDetails getProductDetails() {
		return productDetails;
	}


	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}
    public CartLineInfo() {
        this.quantity = 0;
    }
 
  
    public int getQuantity() {
        return quantity;
    }
 
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
 
    public double getAmount() {
        return this.productDetails.getPrice() * this.quantity;
    }
    
}