package com.shopping.service;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.shopping.model.Product;
import com.shopping.model.ProductDetails;
@Component
public class ProductService {
	
	
	//public static  HashMap
	
	public static HashMap<String,Product> map= new HashMap<>();
	
	public ProductService(){
		
		Product coreJava= new Product("sc100","CoreJava",50);
		Product spring= new Product("sc101","Spring",70);
		Product html= new Product("sc102","HTML",10);
		Product hibernate= new Product("sc103","Hibernate",30);
		Product oracle= new Product("sc104","Oracle",20);
		map.put("sc100",coreJava);
		map.put("sc101",spring);
		map.put("sc102",html);
		map.put("sc103",hibernate);
		map.put("sc104",oracle);
	}
	
	 public Product findProduct(String code){
		 
		return map.get(code);
	 }

}
