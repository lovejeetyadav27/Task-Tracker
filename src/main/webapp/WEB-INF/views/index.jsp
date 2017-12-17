<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
 <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="assets/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
    <link href="assets/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
</head>

<body>
    <nav class="indigo lighten-1" role="navigation">
        <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Gizbell</a>
            <ul class="right hide-on-med-and-down">
                <li class="cart"><a href="#"><i class="large material-icons">add_cart</i></a></li>
            </ul>

            <ul id="nav-mobile" class="side-nav">
                <li class="cart"><a href="#"><i class="medium material-icons">add_cart</i></a></li>
            </ul>
            <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
        </div>
    </nav>
    <div class="section no-pad-bot" id="index-banner">
        <div class="container">
            <h2 class="header center pink-text">DashBoard</h2>
           <!--  <div class="row center">
                <h5 class="header col s12 light">YourKart is one stop online shop to meet all your needs!</h5>
            </div> -->
        </div>
    </div>

<div style="width : 100%;">
<div style="width: 20%; float:left; ">
  <div class="container">
  <div class="card" style="width:100%;">
   <div class="card-block">
   
  <form data-toggle="validator" role="form" >
    <div class="form-group">
      <label for="name" Style="text-decoration: underline;">*TASK NAME:</label>
      <input type="text" class="form-control" name="name" id="name" required></input>
    </div>
    <div class="form-group" >
      <label for="fromdate" Style="text-decoration: underline;">*START DATE:</label>
      <input type="date" class="form-control dateClass" name="fromdate" id="fromdate" required></input>
    </div>
     <div class="form-group">
      <label for="todate" Style="text-decoration: underline;">*END DATE:</label>
      <input type="date" class="form-control dateClass" name="todate" id="todate" required></input>
    </div>
    <div class="form-group">
  <label for="description" Style="text-decoration: underline;">*DESRIPTION:</label>
  <textarea class="form-control" rows="10" id="description" name="desription" required></textarea>
</div>
 <div class="form-group">
    <button type="submit" class="btn btn-primary btn-block savetask">Submit</button>
  </div>
  </form></div></div></div>

</div>


    <div style="width: 60%; float:left; class="container">
        <div class="section">
            <div class="row" id="cards-wrapper">
                <!-- product cards go here from jquery -->
            </div>
            <div class="row inactive" style="max-width: 100000px;"id="summary-wrapper">
                No Tagged Members
               
            </div>
        </div>
    </div>
    
    <div style="width: 20%; float:left; ">
    
      <div class="card card-inverse card-primary mb-3 text-center">
  <div class="card-block">
    <blockquote class="card-blockquote">
      <p style="font-family:courier;text-align:center;">Lists of Tasks you are tagged in</p>
    </blockquote>
  </div>
</div>



 <div id="tagtaskid">
</div>
    
    

    </div>
    </div>




    <footer class="page-footer indigo">
        <div class="container">
            <div class="row">
                <div class="col l6 s12">
                    <h5 class="white-text">Company Bio</h5>
                    <p class="grey-text text-lighten-4">This is the demo dashboard to tag , add and check the tasks you are tagged in</p>
                </div>
            </div>
        </div>
        <div class="footer-copyright">
            <div class="container">
                Made by Lovejeet Yadav!</a>
            </div>
        </div>
    </footer>


     <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.js"></script>
    <script src="assets/js/materialize.js"></script>
    <script src="assets/js/init.js"></script>

</body>

</html>