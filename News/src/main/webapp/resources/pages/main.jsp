<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/resources/css/simplePagination.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/resources/css/modals.css" />" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" charset="UTF-8" src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
	<script type="text/javascript" charset="UTF-8" src="<c:url value="/resources/js/jquery.i18n.properties-min.js" />"></script>
	<script type="text/javascript" charset="UTF-8" src="<c:url value="/resources/js/pageContentFunctions.js" />"></script>
	<script type="text/javascript" charset="UTF-8" src="<c:url value="/resources/js/jquery.simplePagination.js" />"></script>
	<script type="text/javascript" charset="UTF-8" src="<c:url value="/resources/js/modals.js" />"></script>
	<script type="text/javascript" charset="UTF-8" src="<c:url value="/resources/js/validation.js" />"></script>
	<script type="text/javascript" charset="UTF-8" src="<c:url value="/resources/js/moment-with-locales.js" />"></script>
</head>

<body>
	<div class="page-wrap">

	    <div id="header">
	
	        <div id="header_title">
	        </div>
	
	        <div class="lang-widget">
	            <a class="changeLang" href="#" onclick="applyLanguage('en')">English</a> |
	            <a class="changeLang" href="#" onclick="applyLanguage('ru')">Russian</a>
	        </div>
	    </div>
	    
	    <div id="content-wrapper">
	
		    <div id="menu">
		
		        <div class="nav-menu">
		            <div id="nav-menu-title">News</div>
		            <ul id="nav-menu-links">
		            </ul>
		        </div>

			</div>
		
		    <div id="content">
		  	
		    </div>
	    
	    </div>

	</div>

	<footer id="site-footer">
	</footer>
	
</body>
</html>
