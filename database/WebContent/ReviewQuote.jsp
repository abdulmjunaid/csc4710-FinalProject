<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Review Quote</title>
    <style>
        body {
              background-color: #ADD8E6;    
        }
   
    </style>
</head>
<body>
	<a href="activitypage.jsp"target ="_self" > back</a><br><br> 
    <h1>Review Quote</h1>
    <p>Here is the submitted quote:</p>
    <p><strong>Price:</strong> $${param.price}</p>
    <p><strong>Time Frame:</strong> ${param.timeframe}</p>
    <p><strong>Note:</strong> ${param.note}</p>

     <form method="post">
        <input type="submit" name="accept" value="Accept" formaction="DavidQuote.jsp">
        <input type="submit" name="deny" value="Deny" formaction="SubmitQuote.jsp">
        <input type="submit" name="resubmit" value="Resubmit" formaction="SubmitQuote.jsp">
 
    </form>
    
    
</body>
</html>
