<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="/world-mvc-spring/search-by"><button>Search City</button></a>
	<c:forEach items="${continent}" var="continent">
		<h2>
			<a href="/world-mvc-spring/country-list?nameCont=${continent}">${continent}
			</a>
		</h2>
	</c:forEach>
</body>
</html>