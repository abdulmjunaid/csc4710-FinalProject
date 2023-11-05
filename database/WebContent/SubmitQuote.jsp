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
    <div class="form-container">
        <h1>Submit a Quote</h1>
        <form action="ReviewQuote.jsp" method="post">
            <label for="price">Price:</label>
            <input type="number" name="price" id="price" step="1.00" required><br>
            <label for="timeframe">Time Frame:</label>
            <input type="text" name="timeframe" id="timeframe" required><br>
            <label for="note">Note:</label>
            <textarea name="note" id="note" required></textarea><br>
            <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>
    