<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Submit a Quote</title>
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
	<form action="viewQuotes" method="post">
			<table cellpadding="5">
				<tr>
					<td colspan="2">
						<input type="submit" value="Back">
					</td>
				</tr>
			</table>
	</form>
    <div class="form-container">
        <h1>Submit a Quote</h1>
        
        <form action="submitQuote" method="post">
        	<label for="quoteId">Quote ID: </label>
            <input readonly value="${listQuote.quoteId}" type="text" name="quoteId" id="quoteId" required><br><br>
            <label for="quoteId">Client: </label>
            <input size="50"readonly value="${listQuote.clientEmail}" type="text" name="clientEmail" id="clientEmail" required><br><br>
            <label for="price">Price:</label>
            <input type="number" name="price" id="price" step="1.00" required><br><br>
            <label for="timeframe">Time Frame:</label>
            <input type="text" name="timeframe" id="timeframe" required><br><br>
            <label for="note">Note:</label>
            <textarea name="note" id="note" required></textarea><br><br>
            <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>