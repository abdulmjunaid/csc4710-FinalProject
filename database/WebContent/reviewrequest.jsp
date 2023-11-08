<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
    <title>Review Quote</title>
    <style>
        body {
              background-color: #ADD8E6;    
        }
   
    </style>
</head>
<body>
<form action="viewQuotes" method="post">
			<table cellpadding="5">
				<tr>
					<td colspan="2">
						<input type="submit" value="Back">
					</td>
				</tr>
			</table>
	</form>
	
    <h1>Review Request</h1>
    <p>Here is the submitted request:</p>

     <form name="request" id="request" method="post">
   
	     <table id=treeTable>
	     			<tr>
		        		<td>Quote:  <input readonly value="${listQuote.quoteId}" type="text" name="quoteId" id="quoteId" required></td> 
		        	</tr>
		        	<tr>
		        		<td>Client:  <input size="50"readonly value="${listQuote.clientEmail}" type="text" name="clientEmail" id="clientEmail" required></td> 
		        	</tr>
		        <c:forEach var="tree" items="${listTrees}">
		        	<tr>
		        		<td>Tree</td>
		        	</tr>
		            <tr>
		            	<td>	Image One: ${tree.firstPic}</td>
		            </tr>
		            <tr>
		            	<td>	Image Two: ${tree.secondPic}</td>
		            </tr>
		            <tr>
		            	<td>	Image Three: ${tree.thirdPic}</td>
		            </tr>
		            <tr>
		            	<td>	Size: ${tree.size} ft</td>
		            </tr>
		            <tr>
		            	<td>	Height: ${tree.height} ft</td>
		            </tr>
		            <tr>
		            	<td>	Distance: ${tree.distance} ft</td>
		            </tr>
		            <tr>
		            	<td> <br></td>
		            </tr>
		        </c:forEach>
	    </table>
	    <table id=quoteNote>
		            <tr>
		            	<td>	Note: ${listQuote.note}</td>
		            </tr>
		            <tr>
		            	<td> <br></td>
		            </tr>
	     </table>
	    <br>
        <input type="submit" name="deny" onclick="initial1()" value="Deny" >
        <input type="submit" name="inital" onclick="initial()" value="Send Initial Quote" >
 
    </form>
    
    <script>
	    function initial1()
		{
			document.getElementById('request').action = "denyQuote"; 
			console.log("accept");
		}
		function initial()
		{
			document.getElementById('request').action = "sendQuote"; 
			console.log("accept");
		}
	</script>
    
</body>
</html>
