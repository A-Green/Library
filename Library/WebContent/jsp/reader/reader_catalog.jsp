<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="properties.pagecontent"/>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><fmt:message key="label.catalog"/></title>
	<link rel="stylesheet" href="/Library/css/style.css">
</head>
<body>
	<%@include file="/WEB-INF/jspf/reader_header.jspf" %>
	<br/>
	<div align="right">
		<form action="LibraryController" method="post">
			<input type="hidden" name="command" value="search_books"/>
	
			<fmt:message key="label.title"/>
			<input type="text" name="title"/>
			<fmt:message key="label.author"/>
			<input type="text" name="author"/>
	
			<fmt:message key="label.genre"/>
			<select name ="genre">
				<option value="">
				<fmt:message key="label.all"/>
				</option>
				<option value="EDUCATION">
				<fmt:message key="genre.EDUCATION"/>
				</option>
				<option value="DETECTIVE">
				<fmt:message key="genre.DETECTIVE"/>
				</option>
				<option value="KIDS">
				<fmt:message key="genre.KIDS"/>
				</option>
				<option value="FANTASY">
				<fmt:message key="genre.FANTASY"/>
				</option>
				<option value="ADVENTURES">
				<fmt:message key="genre.ADVENTURES"/>
				</option>
			</select>	
			<button class="btn" type="submit"><fmt:message key="label.find"/></button>
		</form>
	</div>
	<br/>
	<form action="LibraryController" method="post">
		<input type="hidden" name="command" value="order_book">
		
		<table class="features-table">	
			<thead>
	    		<tr>
	      			<td><fmt:message key="label.title"/></td>
					<td><fmt:message key="label.genre"/></td>
					<td><fmt:message key="label.author"/></td>
					<td><fmt:message key="label.publisher"/></td>
					<td><fmt:message key="label.year"/></td>
					<td></td>	
	    		</tr>
			</thead>
			<tbody >
					<c:forEach var="book" items="${Books}" >
						<tr>
							<td align="left">${book.title}</td>
							<td align="left"><fmt:message key="genre.${book.genre}"/></td>
							<td> 
								<c:forEach var="author" items="${book.authors}">
									<c:out value="${author.name} "></c:out>
								</c:forEach>
							</td>
							<td>${book.publisher.name} </td>
							<td align="left">${book.year}</td>
							<td>
								<c:choose>
									<c:when test="${book.available == true}">								
										<input type="checkbox" name="selected" value="${book.id}" >							
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="selected" value="${book.id}" disabled="disabled" >
									</c:otherwise>
								</c:choose>
							</td>
						</tr>		
					</c:forEach>
			</tbody>
		</table>
		<br/>
		<div class="container" align="right">
			<select name ="ordersType">
				<option value="ABONEMENT">
					<fmt:message key="ABONEMENT"/>
				</option>
				<option value="READING_ROOM">
					<fmt:message key="READING_ROOM"/>
				</option>
			</select>
			<button class="btn" type="submit"><fmt:message key="label.order"/></button>
		</div>
	</form>
</body>
</html>