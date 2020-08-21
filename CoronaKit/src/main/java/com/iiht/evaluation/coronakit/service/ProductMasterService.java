package com.iiht.evaluation.coronakit.service;

import java.util.List;

import com.iiht.evaluation.coronakit.exception.ProductException;
import com.iiht.evaluation.coronakit.model.ProductMaster;

public interface ProductMasterService {
	ProductMaster validateAndAdd(ProductMaster product) throws ProductException;
	ProductMaster validateAndSave(ProductMaster product) throws ProductException;
	
	boolean deleteProduct(int productId) throws ProductException;
	
	ProductMaster getProduct(int productId) throws ProductException;
	List<ProductMaster> getAllProducts() throws ProductException;
}
