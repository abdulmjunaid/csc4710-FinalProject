<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login to Database</title>
<style>
  body {
    background-color: #ADD8E6;
    color: black; 
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
  }
  .login-container {
    width: 300px;
    padding: 20px;
    background-color: white;
    border-radius: 10px;
    text-align: center;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .input-field {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  .login-button {
    width: 100%;
    padding: 15px;
    background-color: #3498db;
    color: black;
    border: none;
    border-radius: 10px;
    cursor: pointer;
  }
</style>
</head>
<body>
	<div class="login-container">
		<center><h1>Welcome to the Login page</h1></center>
		<p>${loginFailedStr}</p>
		<form action="login" method="post">
			<table cellpadding="5">
				<tr>
					<th>Username:</th>
					<td>
						<input type="text" name="email" class="input-field" autofocus>
					</td>
				</tr>
				<tr>
					<th>Password:</th>
					<td> 
						<input type="password" name="password" class="input-field">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="Login" class="login-button"/>
					</td>
				</tr>
			</table>
			<center><a href="register.jsp" target="_self">Register Here</a></center>
		</form>
	</div>
</body>
</html>
    