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

<form action="root" method="post">
			<table cellpadding="5">
				<tr>
					<td colspan="2">
						<input type="submit" value="Back">
					</td>
				</tr>
			</table>
	</form>

<div align = "center">

<h1>Statistics for ${firstName} ${lastName}</h1>

    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Statistics</h2></caption>
            </tr>
            	<th>Total Number of Trees</th>
            	<th>sdaf</th>
            </tr>
            </tr>
            	<th>Total Due Amount</th>
            	<th>${totalDueAmount}</th>
            </tr>
            </tr>
            	<th>Total Paid Amount</th>
            	<th>${totalPaidAmount}</th>
            </tr>
        </table>
	</div>

	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Tree Statistics</h2></caption>
            <tr>
				<th>Tree ID</th>
                <th>Quote ID</th>
                <th>First Pic</th>
                <th>Second Pic</th>
                <th>Third Pic</th>
                <th>Size</th>
                <th>Height</th>
                <th>Distance</th>
                <th>Date Cut</th>

            </tr>
            <c:forEach var="tree" items="${treeStats}">
                <tr style="text-align:center">
                    <td><c:out value="${tree.treeId}" /></td>
                    <td><c:out value="${tree.quoteId}" /></td>
                    <td><c:out value="${tree.firstPic}" /></td>
                    <td><c:out value="${tree.secondPic}" /></td>
                    <td><c:out value="${tree.thirdPic}" /></td>
                    <td><c:out value="${tree.size}" /></td>
                    <td><c:out value="${tree.height}" /></td>
                    <td><c:out value="${tree.distance}" /></td>
                    <td><c:out value="${tree.date}" /></td>
                </tr>
            </c:forEach>
        </table>
	</div>

</div>

</body>
</html>