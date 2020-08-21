package com.iiht.evaluation.coronakit.dao;

import java.util.List;

import com.iiht.evaluation.coronakit.exception.ProductException;
import com.iiht.evaluation.coronakit.model.ProductMaster;

public interface ProductMasterDao {
	ProductMaster add(ProductMaster product) throws ProductException;
	ProductMaster save(ProductMaster product) throws ProductException;
	boolean deleteById(int productId) throws ProductException;
	
	List<ProductMaster> getAll() throws ProductException;
	ProductMaster getById(int loanId) throws ProductException;
}
