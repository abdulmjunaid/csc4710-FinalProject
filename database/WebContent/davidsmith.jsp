<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Activity Page</title>
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
  <h1>Welcome, David Smith</h1>
        
    
    <form action="viewQuotes">
    <center>
        <a href="login.jsp" target="_self">Logout</a><br><br>
        <input type="submit" value="View Quotes" class="view-qutoes"/><br><br>
    </center>
    </form>
</div>
</body>
</html>
