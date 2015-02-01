<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="lt" uri="/WEB-INF/tld/librarytaglib.tld" %> 
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="properties.pagecontent"/>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><fmt:message key="label.orders"/></title>
		<link rel="stylesheet" href="/Library/css/style.css">
	</head>
<body>
	<%@include file="/WEB-INF/jspf/librarian_header.jspf" %>
	
	<div align="right">
		<form action="LibraryController" method="post">
			<input type="hidden" name="command" value="orders"/>
				<c:if test="${warnMessage != null }">
					<fmt:message key="message.can_not_grant"/>
				</c:if>
				<select name ="ordersStatus">
					<option value="ALL">
						<fmt:message key="label.all"/>
					</option>
					<option value="REQUESTED">
						<fmt:message key="label.requested"/>
					</option>
					<option value="GRANTED">
						<fmt:message key="label.granted"/>
					</option>
					<option value="REJECTED">
						<fmt:message key="label.rejected"/>
					</option>
					<option value="RETURNED">
						<fmt:message key="label.returned"/>
					</option>
				</select>
			<button type="submit" class="btn"><fmt:message key="label.find"/></button>
		</form>
	</div>
	
	<form name="perform_orders" action="LibraryController" method="post">
		<input type="hidden" name="command" >
		<table class="features-table">
				<thead>
					<tr>
						<td><fmt:message key="label.title"/></td>
						<td><fmt:message key="label.date"/></td>
						<td><fmt:message key="label.type"/></td>
						<td><fmt:message key="label.status"/></td>
						<td></td>
						<td></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${Orders}" >
						<tr>
							<td align="left">${order.bookName}</td>
							<td><fmt:formatDate value="${order.orderDate}"/></td>
							<td align="left"><fmt:message key="${order.orderType}"/></td>
							<td align="left"><fmt:message key="${order.status}"/></td>
							<td>
								<c:choose>
									<c:when test="${order.status eq 'GRANTED'}">
										<lt:return-remind returndate="${order.returnDate}"/>
									</c:when>
									<c:otherwise>
										<lt:status-icon status="${order.status}"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td><input type="checkbox" name="selected" value="${order.id};${order.bookId}"></td>
						</tr>		
					</c:forEach>
				</tbody>
		</table>
		<div align="right">
	 		<fmt:message key="label.insert_return_date"/>
	 		<input type="date" name="date" >
	 		<button type="submit" class="btn" onclick="setCommand(perform_orders, 'grant_books'); return false"><fmt:message key="label.grant"/></button>
	 		<button type="submit" class="btn" onclick="setCommand(perform_orders, 'return_books'); return false"><fmt:message key="label.return"/></button>
	 		<button type="submit" class="btn" onclick="setCommand(perform_orders, 'reject_order'); return false"><fmt:message key="label.reject"/></button>
		</div>
	</form>
</body>
</html>