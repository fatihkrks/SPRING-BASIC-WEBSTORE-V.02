<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<section>
      <div class="jumbotron">
         <div class="container">
            <h1 class="alert alert-danger"> There is no product found with the Product id ${exception}</h1>
         </div>
      </div>
   </section>
   <div class="container">
         <p>
						 <a href="<spring:url value="/products"></spring:url>"  class="btn btn-default">
							<span class="glyphicon-hand-left glyphicon"></span> back
						</a>
	
					</p>	
      </div>
</body>
</html>