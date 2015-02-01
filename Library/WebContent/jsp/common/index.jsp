<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="properties.pagecontent"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><fmt:message key="message.greetings"/></title>
		<link rel="stylesheet" href="/Library/css/style.css">
	</head>
	<body class="booksBack">
		<div class="container">
			<form class="form-signin" action="LibraryController" method="post">
				<h2 class="form-signin-heading"><fmt:message key="message.greetings"/></h2>
				<input type="hidden" name="command" value="login" />		
				<input type="text" name="login" placeholder=<fmt:message key="label.login"/>>	
				<br/>
				<input type="password" name="password" placeholder=<fmt:message key="label.password" />>
				<br/>
				<select name ="localeChooser">
					<option value="ru_RU">
					Русский
					</option>
					<option value="en_US">
					English
					</option>
				</select>
				<br/>
				<button class="btn btn-large btn-primary" type="submit">
					<fmt:message key="label.enter" />
				</button>	
				
				<a href="/Library/jsp/common/registration.jsp">
				<fmt:message key="link.registration"/>
				</a>
			</form>		
				
			<c:if test="${warn != null }">
				<fmt:message key="message.wrong_login"/>
			</c:if>
			
			<c:if test="${successRegistr != null }">
				<fmt:message key="message.success_registration"/>
			</c:if>
		</div> 
	</body>
</html>