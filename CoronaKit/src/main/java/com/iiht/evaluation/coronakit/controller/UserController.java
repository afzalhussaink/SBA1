package com.iiht.evaluation.coronakit.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronakit.dao.ProductMasterDao;
import com.iiht.evaluation.coronakit.exception.ProductException;
import com.iiht.evaluation.coronakit.model.CoronaKit;
import com.iiht.evaluation.coronakit.model.KitDetail;
import com.iiht.evaluation.coronakit.model.ProductMaster;
import com.iiht.evaluation.coronakit.service.KitService;
import com.iiht.evaluation.coronakit.service.ProductMasterService;
import com.iiht.evaluation.coronakit.service.ProductMasterServiceImpl;


@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductMasterDao productMasterDao;
	
	private ProductMasterService productService;
	private KitService kitService;
	
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
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "showproducts":
				viewName = showAllProducts(request, response);
				break;	
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = saveOrderForDelivery(request, response);
				break;	
			case "ordersummary":
				viewName = showOrderSummary(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			
			throw new ServletException(ex.getMessage());
		}
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	
	}

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
		
		return view;
	}

	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
		try {
			HttpSession session = request.getSession();
			CoronaKit coronakit = (CoronaKit) session.getAttribute("coronakit");
			List<KitDetail> kitDetails = (List<KitDetail>) session.getAttribute("kitItems");
			coronakit.setDeliveryAddress(request.getParameter("address"));
			request.setAttribute("kitItems", kitDetails);
			request.setAttribute("coronakit", coronakit);
			view = "ordersummary.jsp";
		} catch (Exception e) {
            request.setAttribute("errMsg", e.getMessage());
            view="errorpage.jsp";
        }
		return view;
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
		try {
			List<KitDetail> kitItems = new ArrayList<KitDetail>();
			HttpSession session = request.getSession();
			CoronaKit coronakit = (CoronaKit) session.getAttribute("coronakit");
			String[] selectedItems = request.getParameterValues("itemid");
			String[] givenQuantity = request.getParameterValues("quantity");
			Double totalAmount = 0.0;
			int i =0;
			for (String item:selectedItems) {
				KitDetail kitDetail = new KitDetail();
				kitDetail.setId(i+1);
				kitDetail.setCoronaKitId(1);
				kitDetail.setProductId(Integer.parseInt(item));
				ProductMaster product = productService.getProduct(Integer.parseInt(item));
				kitDetail.setProductName(product.getProductName());
				kitDetail.setQuantity(Integer.parseInt(givenQuantity[i]));
				Double amount = product.getCost()*Integer.parseInt(givenQuantity[i]);
				kitDetail.setAmount(amount);
				i++;
				totalAmount = totalAmount+amount;
				kitItems.add(kitDetail);
			}
			coronakit.setTotalAmount(totalAmount);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("date:"+format.format(new Date()));
			coronakit.setOrderDate(format.format(new Date()));
			session.setAttribute("kitItems", kitItems);
			request.setAttribute("total", totalAmount);
			session.setAttribute("coronakit", coronakit);
			view = "placeorder.jsp";
		} catch (ProductException e) {
            request.setAttribute("errMsg", e.getMessage());
            view="errorpage.jsp";
        }
        return view;
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response)  {
		// HttpSession session = request.getSession();
		String view = "";
		try {
			List<ProductMaster> items = new ArrayList<ProductMaster>();
			String[] selectedProducts = request.getParameterValues("productlist");
			if (selectedProducts == null) {
				List<ProductMaster> products = productService.getAllProducts();
				request.setAttribute("products", products);
				view = "showproductstoadd.jsp";
			} else {
				for (String item : selectedProducts) {
					ProductMaster product = productService.getProduct(Integer.parseInt(item));
					items.add(product);
				}
				List<ProductMaster> products = productService.getAllProducts();
				request.setAttribute("products", products);
				request.setAttribute("items", items);
				view = "showkit.jsp";
			}
		} catch (ProductException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errorpage.jsp";
		}

		return view;
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response) {
		String view="";
        try {
            List<ProductMaster> products = productService.getAllProducts();
            request.setAttribute("products", products);
            view="showproductstoadd.jsp";
        } catch (ProductException e) {
            request.setAttribute("errMsg", e.getMessage());
            view="errorpage.jsp";
        }
        return view;
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
		HttpSession session = request.getSession();
		try {
			CoronaKit coronaKit = new CoronaKit();
			//coronaKit.setId(1001);
			coronaKit.setPersonName(request.getParameter("pname"));
			coronaKit.setContactNumber(request.getParameter("pcontact"));
			coronaKit.setEmail(request.getParameter("pemail"));
			/*
			 * coronaKit.setDeliveryAddress(""); coronaKit.setTotalAmount(0);
			 * coronaKit.setOrderDate(null); coronaKit.setOrderFinalized(false);
			 */
			session.setAttribute("coronakit", coronaKit);
			List<ProductMaster> products = productService.getAllProducts();
            request.setAttribute("products", products);
			view = "showproductstoadd.jsp";
		} catch (ProductException e) {
            request.setAttribute("errMsg", e.getMessage());
            view="errorpage.jsp";
        }
		return view;
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) {
		return "newuser.jsp";
	}
}