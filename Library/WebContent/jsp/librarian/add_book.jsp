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
		<title><fmt:message key="label.add"/></title>
	</head>
<body class="booksBack">
	<%@include file="/WEB-INF/jspf/librarian_header.jspf" %>

	<form action="LibraryController" method="post">
		<input type="hidden" name="command" value="add_Book"/>

		<div class="container" align="left">
			<input type="text" name="title"/>
			<fmt:message key="label.title"/>
			<br/>

			<input type="text" name="authors">
			<fmt:message key="label.author"/>
			<br/>

			<select name ="genre">
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
			<fmt:message key="label.genre"/>
			<br/>

			<input type="text" name="publisher">
			<fmt:message key="label.publisher"/>
			<br/>

			<input type="text" name="year">
			<fmt:message key="label.year"/>
			<br/>
			<button type="submit" class="btn"><fmt:message key="label.add"/></button>

			<c:if test="${bookAddingMessage != null }">
				<br/>
				<fmt:message key="message.book_added_successfully"/>
			</c:if>	
			
			<c:if test="${fillMessage != null }">
				<br/>
				<fmt:message key="message.fill_all_fields"/>
			</c:if>		
		</div>
	</form>
</body>
</html>