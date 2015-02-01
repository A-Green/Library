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
	<title><fmt:message key="label.catalog"/></title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/librarian_header.jspf" %>
	
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
	
		<form action="LibraryController" method="post">
			<input type="hidden" name="command" value="add_Book"/>
			<button type="submit" class="btn"><fmt:message key="label.add" /></button>
			<button type="button" class="btn" onclick="catalog.submit()">
				<fmt:message key="label.remove" />
			</button>
		</form>
	</div>
	
	<form name="catalog" action="LibraryController" method="post">
		<input type="hidden" name="command" value="delete_books"/>
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
			<tbody>
				<c:forEach var="book" items="${Books}" >
					<tr>
						<td align="left">${book.title}</td>
						<td align="left"><fmt:message key="genre.${book.genre}"/></td>
						<td> 
							<c:forEach var="author" items="${book.authors}">
								<c:out value="${author.name}"></c:out>
							</c:forEach>
						</td>
						<td align="left">${book.publisher.name} </td>
						<td align="left">${book.year}</td>
						<td><input type="checkbox" name="selected" value="${book.id}"></td>
					</tr>		
				</c:forEach>
			</tbody>
		</table>
	</form>
</body>
</html>