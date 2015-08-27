<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache,must-revalidate,max_age=0">
<meta http-equiv="Expires" content="0">
<% // used by bootstrap.js %>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="/resources/images/favicon.png">

<title><spring:message code="system.title"/></title>

<% // custom css %>
<!-- Bootstrap core CSS -->
<link href="/resources/css/bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/resources/css/signin.css" rel="stylesheet">
<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->

</head>
<body>
	<script>
		$(document).ready(function() {
			// put your global jquery code here?
		});
	</script>
	
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
     	<tiles:insertAttribute name="header" />
    </div>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          	<tiles:insertAttribute name="menu" />
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <tiles:insertAttribute name="body" />
        </div>
      </div>
    </div>
    
    <tiles:insertAttribute name="footer" />
    <% // custom js 
	   // Bootstrap core JavaScript
       //==================================================
       // Placed at the end of the document so the pages load faster 
    %>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="/resources/js/bootstrap.min.js"></script>    
	<% // ap js %>
</body>
</html>