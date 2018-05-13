<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Shopping Cart</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
 
</head>
<body>
   <jsp:include page="header.jsp" />
  
   <jsp:include page="menu.jsp" />
  
   <fmt:setLocale value="en_US" scope="session"/>
 
   <div class="page-title">My Cart</div>
 
   <c:if test="${empty cartForm or empty cartForm.cartLines}">
       <h2>There is no items in Cart</h2>
       <a href="${pageContext.request.contextPath}/productList.web">Show
           Product List</a>
   </c:if>
 
   <c:if test="${not empty cartForm and not empty cartForm.cartLines }">
    
       <form:form action="/ShopingCartDemo/updateShoppingCart.web" method="POST" modelAttribute="cartForm">
 
           <c:forEach items="${cartForm.cartLines}" var="cartLineInfo"
               varStatus="varStatus">
               <div class="product-preview-container">
                   <ul>
                       <li><img class="product-image"
                           src="${pageContext.request.contextPath}//images/duke.png" />
                       </li>
                       <li>Code: ${cartLineInfo.productDetails.code} <form:hidden
                               path="cartLines[${varStatus.index}].productDetails.code" />
 
                       </li>
                       <li>Name: ${cartLineInfo.productDetails.name}</li>
                       <li>Price: <span class="price">
                      
                         <fmt:formatNumber value="${cartLineInfo.productDetails.price}" type="currency"/>
                        
                       </span></li>
                     
                       <li>Quantity: <form:input
                               path="cartLines[${varStatus.index}].quantity" /></li>
                       <li>Subtotal:
                         <span class="subtotal">
                        
                            <fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/>
                      
                         </span>
                       </li>
                       <li><a href="${pageContext.request.contextPath}/shoppingCartRemoveProduct.web?code=${cartLineInfo.productDetails.code}">
                               Delete </a></li>
                   </ul>
               </div>
           </c:forEach>
           <div style="clear: both"></div>
           <input class="button-update-sc" type="submit" value="Update Quantity" />
           <a class="navi-item"
               href="${pageContext.request.contextPath}/productList.web">Continue
               Buy</a>
       </form:form> 
   </c:if>
 
</body>
</html>