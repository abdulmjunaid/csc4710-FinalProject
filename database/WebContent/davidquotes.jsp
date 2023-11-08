<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
    <title>Quotes</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #ADD8E6;
        }
    </style>
</head>

<body>
<form action="main" method="post">
			<table cellpadding="5">
				<tr>
					<td colspan="2">
						<input type="submit" value="Back">
					</td>
				</tr>
			</table>
	</form>
    <h1>${firstName} Quotes</h1>
    
	
    <table id = openQuotes>
    <caption><h2>List of Open Quotes</h2></caption>
        <tr>
        	<th>Quote ID</th>
            <th>Email</th>
            <th>Price</th>
            <th>Timeframe</th>
            <th>Note</th>
            <th>Status</th>
        </tr>
        <c:forEach var="quote" items="${listOpenQuote}">
            <tr>
            	<td>${quote.quoteId}</td>
                <td>${quote.clientEmail}</td>
                <td>${quote.price}</td>
                <td>${quote.timeFrame}</td>
                <td>${quote.note}</td>
                <td>${quote.status}</td>
            </tr>
        </c:forEach>
    </table>
    
    <table>
	    <caption><h2>List of Requested Quotes</h2></caption>
	        <tr>
	        	<th>Quote ID</th>
	            <th>Email</th>
	            <th>Note</th>
	            <th>Status</th>
	            <th>Review</th>
	        </tr>
	        
	        <c:forEach var="quote" items="${listRequestedQuote}">
	        <form action="reviewRequest" method="post">
	            <tr>
	            	<td><input readonly value="${quote.quoteId}" type="text" name="requestId" id="requestId" required></td>
	                <td>${quote.clientEmail}</td>
	                <td>${quote.note}</td>
	                <td>${quote.status}</td>
	                <td><input type="submit" value="Review Request"></td>
	            </tr> 
	            </form>
	        	
	        </c:forEach>
    </table>
    
    <table id = acceptedQuotes>
    <caption><h2>List of Accepted Quotes</h2></caption>
        <tr>
        	<th>Quote ID</th>
            <th>Email</th>
            <th>Price</th>
            <th>Timeframe</th>
            <th>Note</th>
            <th>Status</th>
        </tr>
        <c:forEach var="quote" items="${listAcceptedQuote}">
            <tr>
            	<td>${quote.quoteId}</td>
                <td>${quote.clientEmail}</td>
                <td>${quote.price}</td>
                <td>${quote.timeFrame}</td>
                <td>${quote.note}</td>
                <td>${quote.status}</td>
            </tr>
        </c:forEach>
    </table>
    
    <table id = rejectedQuotes>
    <caption><h2>List of Rejected Quotes</h2></caption>
        <tr>
        	<th>Quote ID</th>
            <th>Email</th>
            <th>Price</th>
            <th>Timeframe</th>
            <th>Note</th>
            <th>Status</th>
        </tr>
        <c:forEach var="quote" items="${listRejectedQuote}">
            <tr>
            	<td>${quote.quoteId}</td>
                <td>${quote.clientEmail}</td>
                <td>${quote.price}</td>
                <td>${quote.timeFrame}</td>
                <td>${quote.note}</td>
                <td>${quote.status}</td>
            </tr>
        </c:forEach>
    </table>
    
    <script>
      function requestQuotes() {
    	  var table=document.getElementById("requestQuotes");
    	  
      }
      requestQuotes();
      
    </script>
</body>
</html>
    
