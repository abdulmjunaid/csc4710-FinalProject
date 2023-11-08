<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
    <h1>Review Quote</h1>
    <p>Here is the submitted Quote:</p>

     <form name="quote" id="quote" method="post">
	    <table id=quoteNote>
	    			<tr>
		        		<td>Quote:  <input readonly value="${listQuote.quoteId}" type="text" name="quoteId" id="quoteId" required></td> 
		        	</tr>
		        	<tr>
		        		<td>Client:  <input size="50"readonly value="${listQuote.clientEmail}" type="text" name="clientEmail" id="clientEmail" required></td> 
		        	</tr>
	    			<tr>
		            	<td>	Price: ${listQuote.price}</td>
		            </tr>
	    			<tr>
		            	<td>	Time Frame: ${listQuote.timeFrame}</td>
		            </tr>
	    			<tr>
		            	<td>	Note: ${listQuote.status}</td>
		            </tr>
		            <tr>
		            	<td> <br></td>
		            </tr>
	     </table>
	    <br>
	    <input type="submit" name="accept" onclick="initial2()" value="Accept" >
        <input type="submit" name="deny" onclick="initial1()" value="Deny" >
        <input type="submit" name="inital" onclick="initial()" value="Resubmit" >
 
    </form>
    
    <script>
	    function initial2()
		{
			document.getElementById('quote').action = "acceptQuote"; 
			console.log("accept");
		}
	    function initial1()
		{
			document.getElementById('quote').action = "denyQuote"; 
			console.log("deny");
		}
		function initial()
		{
			document.getElementById('quote').action = "sendQuote"; 
			console.log("reply");
		}
	</script>
    
    
</body>
</html>
