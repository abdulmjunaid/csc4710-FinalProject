<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activity page</title>
</head>

<center><h1>David Smith</h1> </center>

 
	<body>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 <a href="SubmitQuote.jsp"target ="_self" >submit a quote</a><br><br> 
		 <p> You can show all the transactions or other attributes here like balance, name of the user and others.</p>
		 </center>
		
	<form action="viewQuotes" method="post">
			<table cellpadding="5">
				<tr>
					<td colspan="2">
						<input type="submit" value="View Quotes">
					</td>
				</tr>
			</table>
	</form>
	
	
	</body>
</html>