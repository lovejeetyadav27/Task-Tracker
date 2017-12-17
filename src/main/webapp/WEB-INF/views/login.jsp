<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>LogiN pAGE</title>
 
      <link rel="stylesheet" href="assets/css/loginstyle.css">

  
</head>

<body>
 <%--  <p><font color="White">${errorMessage}</font></p> --%>
<form action="home" method="POST">
  <header>Login</header>
  <label>Username <span>*</span></label>
  <input id="usernameid" name="username" required></input>
  <div class="help">At least 6 character</div>
  <label>Password <span>*</span></label>
  <input id="passwordid" data-minlength="3" name="password" required></input>
  <div class="help">Use upper and lowercase lettes as well</div>
  <button>Login</button>
  <p><font color="red">Something Went Wrong .Please try again.</font></p>
</form>
  
  
</body>
</html>