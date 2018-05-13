<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
 
</head>
<body>
 
   <jsp:include page="header.jsp" />
   <jsp:include page="menu.jsp" />
  
   <fmt:setLocale value="en_US" scope="session"/>
 
   <div class="page-title">Product List</div>
 
 
 
   <c:forEach items="${paginationProducts}" var="prodInfo">
       <div class="product-preview-container">
           <ul>
               <li><img class="product-image"
                   src="${pageContext.request.contextPath}/images/duke.png" /></li>
               <li>Code: ${prodInfo.code}</li>
               <li>Name: ${prodInfo.name}</li>
               <li>Price: <fmt:formatNumber value="${prodInfo.price}" type="currency"/></li>
               <li><a
                   href="${pageContext.request.contextPath}/buyProduct.web?code=${prodInfo.code}">
                       Add Product</a></li>
              
           </ul>
       </div>
 
   </c:forEach>
   <br/>
</body>
</html>