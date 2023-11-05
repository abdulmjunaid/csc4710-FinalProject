<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>David's Quotes</title>
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
    <h1>David's Quotes</h1>
    <table>
        <tr>
            <th>Email</th>
            <th>Price</th>
            <th>Timeframe</th>
            <th>Note</th>
            <th>Status</th>
        </tr>
        <c:forEach items="${quotes}" var="quote">
            <tr>
                <td>${quote.email}</td>
                <td>${quote.price}</td>
                <td>${quote.timeframe}</td>
                <td>${quote.note}</td>
                <td>${quote.status}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
    

