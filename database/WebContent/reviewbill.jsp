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

<form action="viewBills" method="post">
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
	    <input type="submit" name="accept" onclick="initial2()" value="Pay" >
        <input type="submit" name="reject" onclick="initial()" value="Reject" >
 
    </form>
    
    <script>
	    function initial2()
		{
			document.getElementById('bill').action = "payBill"; 
			console.log("accept");
		}
		function initial()
		{
			document.getElementById('bill').action = "rejectBillPage"; 
			console.log("reject");
		}
	</script>
    
    
</body>
</html>
