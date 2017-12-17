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
  
<form action="home" method="POST">
  <header>Login</header>
  <label>Username <span>*</span></label>
 <input id="usernameid" name="username" required></input>
  <div class="help">At least 6 character</div>
  <label>Password <span>*</span></label>
  <input id="passwordid" name="password" required></input>
  <div class="help">Use upper and lowercase lettes as well</div>
  <button>Login</button>
</form>
  
  
</body>
</html>
