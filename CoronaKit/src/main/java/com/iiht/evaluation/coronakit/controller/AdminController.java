package com.iiht.evaluation.coronakit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.coronakit.dao.ProductMasterDao;
import com.iiht.evaluation.coronakit.exception.ProductException;
import com.iiht.evaluation.coronakit.model.ProductMaster;
import com.iiht.evaluation.coronakit.service.ProductMasterService;
import com.iiht.evaluation.coronakit.service.ProductMasterServiceImpl; 

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductMasterDao productMasterDao;
	
	private ProductMasterService productService;
	
	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	@Override
    public void init() throws ServletException {
		productService=new ProductMasterServiceImpl();
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		String viewName = "";
		try {
			switch (action) {
			case "login" : 
				viewName = adminLogin(request, response);
				break;
			case "newproduct":
				viewName = showNewProductForm(request, response);
				break;
			case "insertproduct":
				viewName = insertProduct(request, response);
				break;
			case "deleteproduct":
				viewName = deleteProduct(request, response);
				break;
			case "editproduct":
				viewName = showEditProductForm(request, response);
				break;
			case "updateproduct":
				viewName = updateProduct(request, response);
				break;
			case "list":
				viewName = listAllProducts(request, response);
				break;	
			case "logout":
				viewName = adminLogout(request, response);
				break;	
			default : 
				viewName = "notfound.jsp"; 
				break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String listAllProducts(HttpServletRequest request, HttpServletResponse response) {
		String view="";
        try {
            List<ProductMaster> products = productService.getAllProducts();
            request.setAttribute("products", products);
            view="listproducts.jsp";
        } catch (ProductException e) {
            request.setAttribute("errMsg", e.getMessage());
            view="errPage.jsp";
        }
        return view;
	}

	private String updateProduct(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
        int productId = Integer.parseInt(request.getParameter("id"));
        try {
        	ProductMaster product = productService.getProduct(productId);
            request.setAttribute("product", product);
            request.setAttribute("isNew", false);
            view = "editproduct.jsp";
        } catch (ProductException e) {
            request.setAttribute("errMsg", e.getMessage());
            view = "errPage.jsp";
        }
        return view;
	}

	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
        int productId = Integer.parseInt(request.getParameter("id"));
        try {
            productService.deleteProduct(productId);
            request.setAttribute("msg", "Product Deleted!");
            view = "index.jsp";
        } catch (ProductException e) {
            request.setAttribute("errMsg", e.getMessage());
            view = "errorpage.jsp";
        }
        return view;
	}

	private String insertProduct(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
        ProductMaster product = new ProductMaster();
        request.setAttribute("product", product);
        request.setAttribute("isNew", true);
        view = "newproduct.jsp";
        return view;
	}

	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String adminLogin(HttpServletRequest request, HttpServletResponse response) {
		return "";
	}

	
}