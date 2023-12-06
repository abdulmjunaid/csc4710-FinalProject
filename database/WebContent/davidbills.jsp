<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
    <title>Bills</title>
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
    <h1>${firstName} Bills</h1>
    
    <table id=issueBill name=issueBill>
    <caption><h2>Issue Bill</h2></caption>
        <tr>
        	<th>Quote ID</th>
            <th>Email</th>
            <th>Price</th>
            <th>Timeframe</th>
            <th>Note</th>
            <th>Status</th>
            <th>Current</th>
            <th>Reply</th>
        </tr>
        <c:forEach var="quote" items="${listIssueBills}">
        <form action="issueBillPage" id="${quote.quoteId}" method="post">
            <tr>
            	<td><input readonly value="${quote.quoteId}" type="text" name="quoteId" id="quoteId" required></td>
                <td>${quote.clientEmail}</td>
                <td>${quote.price}</td>
                <td>${quote.timeFrame}</td>
                <td>${quote.note}</td>
                <td>${quote.status}</td>
                <td>${quote.current}</td>
                <td><input type="submit" value="Issue Bill"></td>    
            </tr>
        </form>
        </c:forEach>
    </table>
	
    <table id=openBills name=openBills>
    <caption><h2>List of Open Bills</h2></caption>
        <tr>
        	<th>Bill ID</th>
        	<th>Quote ID</th>
            <th>Email</th>
            <th>Price</th>
            <th>Note</th>
            <th>Status</th>
            <th>Current</th>
            <th>Reply</th>
        </tr>
        <c:forEach var="bill" items="${listOpenBill}">
        <form action="davidReviewBill" id="${bill.billId}" method="post">
            <tr>
            	<td><input readonly value="${bill.billId}" type="text" name="billId" id="billId" required></td>
                <td>${bill.quoteId}</td>
                <td>${bill.clientEmail}</td>
                <td>${bill.price}</td>
                <td>${bill.note}</td>
                <td>${bill.status}</td>
                <td>${bill.current}</td>
                <td><input type="submit" value="Reply"></td>    
            </tr>
        </form>
        </c:forEach>
    </table>
    
    <table>
    <caption><h2>List of Accepted Bills</h2></caption>
        <tr>
        	<th>Bill ID</th>
        	<th>Quote ID</th>
            <th>Email</th>
            <th>Price</th>
            <th>Note</th>
            <th>Status</th>
        </tr>
        <c:forEach var="bill" items="${listAcceptedBill}">
            <tr>
            	<td>${bill.billId}</td>
            	<td>${bill.quoteId}</td>
            	<td>${bill.clientEmail}</td>
                <td>${bill.price}</td>
                <td>${bill.note}</td>
                <td>${bill.status}</td>
            </tr>
        </c:forEach>
    </table>
    
    
    <script>
    function replyButton(){
    	var reply = document.createElement("input");
    	reply.setAttribute("type", "submit");
    	reply.setAttribute("value", "Reply")
    	
    	var table=document.getElementById("openBills");
    	var r=1; //start counting rows in table
    	while(row=table.rows[r++])
    	{
    	  var c=7; //start counting columns in row
    	  cell=row.cells[7];
    	  if(row.cells[6].innerText != ("davidsmith@treecutters.com")){
    		  row.cells[7].innerHTML='Awaiting Response'; // do sth with cell
    	  }
    	}
    }
    replyButton();
      
    </script>
</body>
</html>
    

