package com.iiht.evaluation.coronakit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronakit.exception.ProductException;
import com.iiht.evaluation.coronakit.model.ProductMaster;

public class ProductMasterDaoJdbcImpl implements ProductMasterDao {
	
	public static final String INS_PROD_QRY = "INSERT INTO products(pid,pname,pdesc,pcost) VALUES(?,?,?,?)";
	public static final String UPD_PROD_QRY = "UPDATE products SET pname=?,pdesc=?,pcost=? WHERE pid=?";
	public static final String DEL_PROD_QRY = "DELETE FROM products WHERE pid=?";
	public static final String GET_BY_ID_PROD_QRY = "SELECT pid,pname,pdesc,pcost FROM products WHERE pid=?";
	public static final String GET_ALL_PROD_QRY = "SELECT pid,pname,pdesc,pcost FROM products";

	@Override
	public ProductMaster add(ProductMaster product) throws ProductException {
		if (product != null) {
			try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(INS_PROD_QRY);) {

				pst.setInt(1, product.getId());
				pst.setString(2, product.getProductName());
				pst.setString(3, product.getProductDescription());
				pst.setDouble(4, product.getCost());
				pst.executeUpdate();

			} catch (SQLException exp) {
				throw new ProductException("An error occured, Could not add the product details!");
			}
		}
		return product;
	}

	@Override
	public ProductMaster save(ProductMaster product) throws ProductException {
		if (product != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(UPD_PROD_QRY);) {
				
				pst.setString(1, product.getProductName());
				pst.setString(2, product.getProductDescription());
				pst.setDouble(3, product.getCost());
				pst.setInt(4, product.getId());

				pst.executeUpdate();

			} catch (SQLException exp) {
				throw new ProductException("An error occured, Could not save the product details!");
			}
		}
		return product;
	}

	@Override
	public boolean deleteById(int productId) throws ProductException {
		boolean isDeleted = false;

		try (Connection con = ConnectionFactory.getConnection();
			PreparedStatement pst = con.prepareStatement(DEL_PROD_QRY);) {

			pst.setInt(1, productId);

			int rowsCount = pst.executeUpdate();
			
			isDeleted= rowsCount>0 ;

		} catch (SQLException exp) {
			throw new ProductException("An error occured, Could not delete the product details!");
		}

		return isDeleted;
	}

	@Override
	public List<ProductMaster> getAll() throws ProductException {
		System.out.println("in getAll");
		List<ProductMaster> products = new ArrayList<>();
		
		try (Connection con = ConnectionFactory.getConnection();
			PreparedStatement pst = con.prepareStatement(GET_ALL_PROD_QRY);) {		

			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				ProductMaster product = new ProductMaster();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductDescription(rs.getString(3));
				product.setCost(rs.getDouble(4));
				System.out.println("Fetched product:"+product);			
				products.add(product);
			}
			System.out.println("products list:"+products);
			if(products.isEmpty()) {
				products=null;
			}
		} catch (SQLException exp) {
			throw new ProductException("An error occured, Could not retrive the product details!");
		}
		System.out.println("returning products list:"+products);	
		return products;
	}

	@Override
	public ProductMaster getById(int productId) throws ProductException {
		ProductMaster product=null;
		
		try (Connection con = ConnectionFactory.getConnection();
			PreparedStatement pst = con.prepareStatement(GET_BY_ID_PROD_QRY);) {		

			pst.setInt(1, productId);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				product = new ProductMaster();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductDescription(rs.getString(3));
				product.setCost(rs.getDouble(4));
			}
			
		} catch (SQLException exp) {
			throw new ProductException("An error occured, Could not retrive the loan details!");
		}
		
		return product;
	}

}
