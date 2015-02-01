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
		<title><fmt:message key="label.registration"/></title>
	</head>
<body class="booksBack">
	<div class="container">
		<form action="/Library/LibraryController" method="post">
			<h2><fmt:message key="message.register"/></h2>
			<input type="hidden" name="command" value="registration">
			
			<input type="text" name="login">
			<fmt:message key="label.login"/>
			
			<c:if test="${loginWarn != null }">
				<br/>
				<fmt:message key="message.login_uses"/>
			</c:if>
			
			<br/>
			<input type="password" name="password">
			<fmt:message key="label.password" />
			
			<br/>
			<input type="password" name="repass">
			<fmt:message key="message.repeat_pass" />
			
			<c:if test="${passWarn != null }">
				<br/>
				<fmt:message key="message.pass_not_matches"/>
			</c:if>
	
			<br/>
			<input type="text" name="name">
			<fmt:message key="label.name" />
			
			<br/>
			
			<input type="text" name="country">
			<fmt:message key="label.country" />	
			
			<br/>
			<input type="text" name="city">
			<fmt:message key="label.city" />
			
			<br/>
			<input type="text" name="street">
			<fmt:message key="label.street" />
			
			<br/>
			<input type="text" name="phone">
			<fmt:message key="label.phone" />
			
			<c:if test="${phoneWarn != null }">
				<br/>
				<fmt:message key="message.invalid_phone"/>
			</c:if>
			
			<br/>
			<input type="text" name="email">
			<fmt:message key="label.email" />
			
			<c:if test="${emailWarn != null }">
				<br/>
				<fmt:message key="message.book_added_successfully"/>
			</c:if>
			<br/>
			
			<select name ="localeChooser">
				<option value="ru_RU">
				Русский
				</option>
				<option value="en_US">
				English
				</option>
			</select>
			
			<c:if test="${empryFieldsWarn != null }">
				<br/>
				<fmt:message key="message.empty_fields"/>
				<br/>
			</c:if>
			<br/>
			<button class="btn btn-large btn-primary" type="submit">
				<fmt:message key="label.register" />
			</button>	
		</form>
	</div>
</body>
</html>