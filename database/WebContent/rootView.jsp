<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
<style>
  body {
    background-color: #ADD8E6;
    color: white; 

  }
</style>
</head>
<body>

<div align = "center">
	
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
            	<th>Role</th>
				<th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Adress</th>
                <th>Credit Card</th>
                <th>Phone Number</th>
                <th>Password</th>
                <th>Statistics</th>

            </tr>
            <c:forEach var="users" items="${listUser}">
            <form action="statistics" id="${users.email}" method="post">
                <tr style="text-align:center">
                    <td><c:out value="${users.role}" /></td>
                    <td><input readonly value="${users.email}" type="text" name="email" id="email" required></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value= "${users.adress_street_num} ${users.adress_street} ${users.adress_city} ${users.adress_state} ${users.adress_zip_code}" /></td>
                    <td><c:out value="${users.creditCard}"/></td>
                    <td><c:out value="${users.phoneNumber}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><input type="submit" value="View Statistics"></td> 
                </tr>
            </form>
            </c:forEach>
        </table>
	</div>
	
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Big Clients</h2></caption>
            <tr>
				<th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Phone Number</th>

            </tr>
            <c:forEach var="client" items="${bigClientList}">
                <tr style="text-align:center">
                    <td><c:out value="${client.email}" /></td>
                    <td><c:out value="${client.firstName}" /></td>
                    <td><c:out value="${client.lastName}" /></td>
                    <td><c:out value="${client.phoneNumber}" /></td>
                </tr>
            </c:forEach>
        </table>
	</div>
	
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Easy Clients</h2></caption>
            <tr>
				<th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Phone Number</th>

            </tr>
            <c:forEach var="client" items="${easyClients}">
                <tr style="text-align:center">
                    <td><c:out value="${client.email}" /></td>
                    <td><c:out value="${client.firstName}" /></td>
                    <td><c:out value="${client.lastName}" /></td>
                    <td><c:out value="${client.phoneNumber}" /></td>
                </tr>
            </c:forEach>
        </table>
	</div>
	
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>One Tree Quotes</h2></caption>
            <tr>
        	<th>Quote ID</th>
            <th>Email</th>
            <th>Price</th>
            <th>Timeframe</th>
            <th>Note</th>
            <th>Status</th>
	        </tr>
	        <c:forEach var="quote" items="${oneTreeQuotes}">
	            <tr>
	            	<td>${quote.quoteId}</td>
	                <td>${quote.clientEmail}</td>
	                <td>${quote.price}</td>
	                <td>${quote.timeFrame}</td>
	                <td>${quote.note}</td>
	                <td>${quote.status}</td>
	            </tr>
        </c:forEach>
        </table>
	</div>
	
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Prospective Clients</h2></caption>
            <tr>
				<th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Phone Number</th>

            </tr>
            <c:forEach var="client" items="${prospectiveClients}">
                <tr style="text-align:center">
                    <td><c:out value="${client.email}" /></td>
                    <td><c:out value="${client.firstName}" /></td>
                    <td><c:out value="${client.lastName}" /></td>
                    <td><c:out value="${client.phoneNumber}" /></td>
                </tr>
            </c:forEach>
        </table>
	</div>
	
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Highest Trees</h2></caption>
            <tr>
				<th>Tree ID</th>
                <th>Quote ID</th>
                <th>firstPic</th>
                <th>secondPic</th>
                <th>thirdPic</th>
                <th>size</th>
                <th>height</th>
                <th>distance</th>

            </tr>
            <c:forEach var="tree" items="${highestTrees}">
                <tr style="text-align:center">
                    <td><c:out value="${tree.treeId}" /></td>
                    <td><c:out value="${tree.quoteId}" /></td>
                    <td><c:out value="${tree.firstPic}" /></td>
                    <td><c:out value="${tree.secondPic}" /></td>
                    <td><c:out value="${tree.thirdPic}" /></td>
                    <td><c:out value="${tree.size}" /></td>
                    <td><c:out value="${tree.height}" /></td>
                    <td><c:out value="${tree.distance}" /></td>
                </tr>
            </c:forEach>
        </table>
	</div>
	
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Overdue Bills</h2></caption>
            <tr>
            <th>Bill ID</th>
        	<th>Quote ID</th>
            <th>Email</th>
            <th>Price</th>
            <th>Note</th>
            <th>Status</th>
	        </tr>
	        <c:forEach var="bill" items="${overdueBills}">
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
	</div>
	
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Bad Clients</h2></caption>
            <tr>
				<th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Phone Number</th>

            </tr>
            <c:forEach var="client" items="${badClients}">
                <tr style="text-align:center">
                    <td><c:out value="${client.email}" /></td>
                    <td><c:out value="${client.firstName}" /></td>
                    <td><c:out value="${client.lastName}" /></td>
                    <td><c:out value="${client.phoneNumber}" /></td>
                </tr>
            </c:forEach>
        </table>
	</div>
	
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Good Clients</h2></caption>
            <tr>
				<th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Phone Number</th>

            </tr>
            <c:forEach var="client" items="${goodClients}">
                <tr style="text-align:center">
                    <td><c:out value="${client.email}" /></td>
                    <td><c:out value="${client.firstName}" /></td>
                    <td><c:out value="${client.lastName}" /></td>
                    <td><c:out value="${client.phoneNumber}" /></td>
                </tr>
            </c:forEach>
        </table>
	</div>

</div>

</body>
</html>