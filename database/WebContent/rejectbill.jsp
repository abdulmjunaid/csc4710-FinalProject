<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reject Bill</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: flex-start; 
            height: 100vh;
            margin: 0;
            background-color: #ADD8E6;

        }
        .form-container {
            text-align: center;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
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
    <div class="form-container">
        <h1>Reject Bill</h1>
        
        <form action="rejectBill" method="post">
        	<label for="quoteId">Bill ID: </label>
            <input readonly value="${listBill.billId}" type="text" name="billId" id="billId" required><br><br>
        	<label for="quoteId">Quote ID: </label>
            <input readonly value="${listBill.quoteId}" type="text" name="quoteId" id="quoteId" required><br><br>
            <label for="note">Note:</label>
            <textarea name="note" id="note" required></textarea><br><br>
            <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>