<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="/Library/css/style.css">
	<title><fmt:message key="label.clients"/></title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/librarian_header.jspf" %>
	
	<form action="LibraryController" method="post">
		<input type="hidden" name="command" value="clients">
		<div align="right">
			<input type="text" name="clientName">
			<button type="submit" class="btn"><fmt:message key="label.find"/></button>
		</div>
	</form>

	<form name="reader_orders" action="LibraryController" method="post">
		<input type="hidden" name="command" value="orders">
		<input type="hidden" name="readerId">
		<table class="features-table">
			<thead>
				<tr>
					<td><fmt:message key="label.client"/></td>
					<td><fmt:message key="label.phone"/></td>
					<td><fmt:message key="label.email"/></td>
					<td><fmt:message key="label.street"/></td>
					<td><fmt:message key="label.city"/></td>
					<td><fmt:message key="label.country"/></td>
				</tr>		
			</thead>
			<tbody>
				<c:forEach var="client" items="${Clients}" >	
					<tr>				
						<td>
							<a href="#" onclick="{document.reader_orders.readerId.value=${client.id};document.reader_orders.submit();}">
							${client.name}
							</a>
						</td>
						<td>${client.contactInfo.phone}</td>
						<td>${client.contactInfo.email}</td>
						<td>${client.contactInfo.street}</td>
						<td>${client.contactInfo.city}</td>
						<td>${client.contactInfo.country}</td>
					</tr>					
				</c:forEach>
			</tbody>
		</table>
	</form>
</body>
</html>