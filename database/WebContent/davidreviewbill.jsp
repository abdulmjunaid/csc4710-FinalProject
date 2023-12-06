<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Review Bill</title>
    <style>
        body {
              background-color: #ADD8E6;    
        }
   
    </style>
</head>
<body>

<form action="viewBill" method="post">
			<table cellpadding="5">
				<tr>
					<td colspan="2">
						<input type="submit" value="Back">
					</td>
				</tr>
			</table>
	</form>
	
    <h1>Review Bill</h1>
    <p>Here is the submitted Bill:</p>

     <form name="bill" id="bill" method="post">
	    <table id=billNote>
	    			<tr>
		        		<td>Bill:  <input readonly value="${listBill.billId}" type="text" name="billId" id="billId" required></td> 
		        	</tr>
		        	<tr>
		        		<td>Quote:  <input size="50"readonly value="${listBill.quoteId}" type="text" name="quoteId" id="quoteId" required></td> 
		        	</tr>
		        	<tr>
		        		<td>Client:  <input size="50"readonly value="${listBill.clientEmail}" type="text" name="clientEmail" id="clientEmail" required></td> 
		        	</tr>
	    			<tr>
		            	<td>	Price: ${listBill.price}</td>
		            </tr>
	    			<tr>
		            	<td>	Note: ${listBill.note}</td>
		            </tr>
		            <tr>
		            	<td> <br></td>
		            </tr>
	     </table>
	    <br>
        <input type="submit" name="inital" onclick="initial()" value="Reply" >
 
    </form>
    
    <script>
		function initial()
		{
			document.getElementById('bill').action = "replyBillPage"; 
			console.log("reply");
		}
	</script>
    
    
</body>
</html>
