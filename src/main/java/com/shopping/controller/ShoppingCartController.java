package com.shopping.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.shopping.form.LoginForm;
import com.shopping.model.CartInfo;
import com.shopping.model.Product;
import com.shopping.model.ProductDetails;
import com.shopping.service.ProductService;
import com.shopping.utils.ShoppingCartUtils;

@Controller
public class ShoppingCartController {

	private static final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

	@Value("${shopping.userid}")
	private String userId;
	@Value("${shopping.password}")
	private String password;

	@Autowired
	ProductService produtService;

	@RequestMapping(value = "/login.web", method = RequestMethod.GET)
	public ModelAndView init(ModelAndView model) {
		return new ModelAndView("login", "loginForm", new LoginForm());
	}

	@RequestMapping(value = "/autenticate.web", method = RequestMethod.POST)
	public String AuthenticateUser(Model model, @ModelAttribute("loginForm") LoginForm loginForm) {
		String userIds[] = null;
		String passwords[] = null;
		logger.info("AuthenticateUser , event=REQUEST");
		if (userId != null) {

			userIds = userId.split(",");
		}
		if (password != null) {

			passwords = password.split(",");
		}

		if ((loginForm.getUserName() != null && loginForm.getUserName().length() > 0)
				&& (loginForm.getPassword() != null && loginForm.getPassword().length() > 0)) {

			if ((userIds[0].equalsIgnoreCase(loginForm.getUserName())|| userIds[1].equalsIgnoreCase(loginForm.getUserName()))
					&& (passwords[0].equalsIgnoreCase(loginForm.getPassword())|| passwords[1].equalsIgnoreCase(loginForm.getPassword()))) {
				logger.info("AuthenticateUser , event=RESPONSE");
				return "home";
			} else {

				logger.error("UnAutherized User : {} ", loginForm.getUserName());

				return "error";
			}
		} else {
			model.addAttribute("error", "Please enter Details");
			return "login";
		}

	}

	@RequestMapping(value = "/home.web", method = RequestMethod.GET)
	public String home() {

		return "home";
	}

	@RequestMapping(value = "/logout.web", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) {
		logger.info("logout , event=REQUEST");
		ShoppingCartUtils.removeCartInSession(request);
		logger.info("logout , event=RESPONSE");
		return "redirect:/login.web";

	}

	// List products
	@RequestMapping(value = "/productList.web")
	public String listProductHandler(Model model, HttpServletRequest request) {
		logger.info("listProductHandler , event=REQUEST");
		List<ProductDetails> productList = new ArrayList<>();
		ProductDetails coreJava = new ProductDetails("sc100", "CoreJava", 50);
		ProductDetails spring = new ProductDetails("sc101", "Spring", 70);
		ProductDetails html = new ProductDetails("sc102", "HTML", 10);
		ProductDetails hibernate = new ProductDetails("sc103", "Hibernate", 30);
		ProductDetails oracle = new ProductDetails("sc104", "Oracle", 20);
		productList.add(coreJava);
		productList.add(spring);
		productList.add(html);
		productList.add(hibernate);
		productList.add(oracle);
		model.addAttribute("paginationProducts", productList);
		logger.info("listProductHandler , event=RESPONSE");
		return "productList";

	}

	// Add products to shoping cart
	@RequestMapping(value = "/buyProduct.web")
	public String addProducts(HttpServletRequest request, Model model, //
			@RequestParam(value = "code", defaultValue = "") String code) {
		logger.info("addProducts , event=REQUEST");
		Product product = null;
		if (code != null && code.length() > 0) {
			product = produtService.findProduct(code);
		}
		if (product != null) {

			// Cart info stored in Session.
			CartInfo cartInfo = ShoppingCartUtils.getCartInSession(request);

			ProductDetails productDetails = new ProductDetails(product.getCode(), product.getName(),
					product.getPrice());

			cartInfo.addProduct(productDetails, 1);
		}
		logger.info("addProducts , event=RESPONSE");
		// Redirect to shoppingCart page.
		return "redirect:/shoppingCart.web";
	}

	@RequestMapping(value = "/shoppingCart.web", method = RequestMethod.GET)
	public String shoppingCartHandler(HttpServletRequest request, Model model) {
		CartInfo myCart = ShoppingCartUtils.getCartInSession(request);
		model.addAttribute("cartForm", myCart);
		return "shoppingCart";
	}

	// POST: Update quantity of products in cart.
	@RequestMapping(value = "/updateShoppingCart.web", method = RequestMethod.POST)
	public String shoppingCartUpdateProducts(HttpServletRequest request, //
			Model model, //
			@ModelAttribute("cartForm") CartInfo cartForm) {
		logger.info("shoppingCartUpdateProducts , event=REQUEST");
		CartInfo cartInfo = ShoppingCartUtils.getCartInSession(request);
		cartInfo.updateQuantity(cartForm);
		logger.info("shoppingCartUpdateProducts , event=RESPONSE");
		// Redirect to shoppingCart page.
		return "redirect:/shoppingCart.web";
	}

	// Remove product from shopping cart
	@RequestMapping("/shoppingCartRemoveProduct.web")
	public String removeProductHandler(HttpServletRequest request, Model model, //
			@RequestParam(value = "code", defaultValue = "") String code) {
		Product product = null;
		logger.info("removeProductHandler , event=REQUEST");
		if (code != null && code.length() > 0) {
			product = produtService.findProduct(code);
		}
		if (product != null) {

			// Cart Info stored in Session.
			CartInfo cartInfo = ShoppingCartUtils.getCartInSession(request);

			ProductDetails productDetails = new ProductDetails(product.getCode(), product.getName(),
					product.getPrice());

			cartInfo.removeProduct(productDetails);
			logger.info("removeProductHandler , event=RESPONSE");

		}
		// Redirect to shoppingCart page.
		return "redirect:/shoppingCart.web";
	}

}
