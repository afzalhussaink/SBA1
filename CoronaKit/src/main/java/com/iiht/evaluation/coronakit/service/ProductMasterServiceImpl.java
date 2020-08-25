package com.iiht.evaluation.coronakit.service;

import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronakit.dao.ProductMasterDao;
import com.iiht.evaluation.coronakit.dao.ProductMasterDaoJdbcImpl;
import com.iiht.evaluation.coronakit.exception.ProductException;
import com.iiht.evaluation.coronakit.model.ProductMaster;

public class ProductMasterServiceImpl implements ProductMasterService {
	
	ProductMasterDao productDao;
	
	public ProductMasterServiceImpl() {
		productDao=new ProductMasterDaoJdbcImpl();
	}

	private boolean isValidProductId(Integer productId) {
		return productId!=null && productId>0;
	}
	
	private boolean isValidName(String name) {
		return name!=null && (name.length()>=3 && name.length()<=50);
	}
	
	private boolean isValidDescription(String desc) {
		return desc!=null && (desc.length()>=3 && desc.length()<=200);
	}
	
	private boolean isValidCost(Double cost) {
		return cost!=null && cost>0;
	}
	
	private boolean isValidProduct(ProductMaster product) throws ProductException{
		boolean isValid=true;
		
		List<String> errMsgs = new ArrayList<>();
		
		if(product!=null) {
			if(!isValidProductId(product.getId())) {
				isValid=false;
				errMsgs.add("Product Id must be a positive non-repetative number");
			}
			if(!isValidName(product.getProductName())) {
				isValid=false;
				errMsgs.add("Product Name must be of 3 to 50 chars in length");
			}
			if(!isValidDescription(product.getProductDescription())) {
				isValid=false;
				errMsgs.add("Product Descrption must be of 3 to 200 chars in length");
			}
			if(!isValidCost(product.getCost())) {
				isValid=false;
				errMsgs.add("Cost must be a positive non-zero number");
			}
			if(!errMsgs.isEmpty()) {
				throw new ProductException(errMsgs.toString());
			}
		}else {
			isValid=false;
		}
		
		return isValid;
	}


	@Override
	public ProductMaster validateAndAdd(ProductMaster product) throws ProductException {
		if(isValidProduct(product)) {
			productDao.add(product);
		}
		return product;
	}

	@Override
	public ProductMaster validateAndSave(ProductMaster product) throws ProductException {
		if(isValidProduct(product)) {
			productDao.save(product);
		}
		return product;
	}

	@Override
	public boolean deleteProduct(int productId) throws ProductException {
		return productDao.deleteById(productId);
	}

	@Override
	public ProductMaster getProduct(int productId) throws ProductException {
		return productDao.getById(productId);
	}

	@Override
	public List<ProductMaster> getAllProducts() throws ProductException {
		return productDao.getAll();
	}

}
