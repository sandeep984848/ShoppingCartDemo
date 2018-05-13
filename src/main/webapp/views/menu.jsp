<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>   
  --%>
 
<div class="menu-container">
  
   <a href="${pageContext.request.contextPath}/home.web">Home</a>
   |
   <a href="${pageContext.request.contextPath}/productList.web">
      Product List
   </a>
   |
   <a href="${pageContext.request.contextPath}/shoppingCart.web">
      My Cart
   </a>
   |
   <%-- <security:authorize  access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
     <a href="${pageContext.request.contextPath}/orderList">
         Order List
     </a>
     |
   </security:authorize> --%>
   
  
</div>