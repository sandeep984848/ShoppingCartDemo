package com.shopping.utils;

import javax.servlet.http.HttpServletRequest;

import com.shopping.model.CartInfo;

public class ShoppingCartUtils {
	
	
	  // Products in Cart, stored in Session.
    public static CartInfo getCartInSession(HttpServletRequest request) {
 
        // Get Cart from Session.
        CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCart");
        
        // If null, create it.
        if (cartInfo == null) {
            cartInfo = new CartInfo();
            
            // And store to Session.
            request.getSession().setAttribute("myCart", cartInfo);
        }
 
        return cartInfo;
    }
    
    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().invalidate();
    }

}
