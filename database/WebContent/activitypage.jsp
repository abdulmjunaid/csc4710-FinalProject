<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Activity page</title>
    <style>
        body {
            background-color: #ADD8E6;
            color: #333;
            font-family: Arial, sans-serif;
        }

        h1 {
            text-align: center;
            color: #007BFF;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #FFF;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        a {
            text-decoration: none;
            background-color: #007BFF;
            color: #FFF;
            padding: 10px 20px;
            border-radius: 5px;
        }

        a:hover {
            background-color: #0056b3;
        }

        p {
            margin-top: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
  <h1>Welcome to the Activity Page</h1>
    
                <p>Welcome, ${sessionScope.userName}!</p>
      
    <center>
    <a href="login.jsp" target="_self">Logout</a><br><br>
    
    <form action="viewQuotes">
        <input type="submit" value="View Quotes" class="view-qutoes"/><br><br>
    </form>
    
    <form action="viewBills">
        <input type="submit" value="View Bills" class="view-bills"/><br><br>
    </form>
    </center>
 
</div>
</body>
</html>
