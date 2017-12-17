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
        <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">YK</a>
            <ul class="right hide-on-med-and-down">
                <li class="cart"><a href="#"><i class="large material-icons">add_shopping_cart</i></a></li>
            </ul>

            <ul id="nav-mobile" class="side-nav">
                <li class="cart"><a href="#"><i class="medium material-icons">add_shopping_cart</i></a></li>
            </ul>
            <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
        </div>
    </nav>
    <div class="section no-pad-bot" id="index-banner">
        <div class="container">
            <br><br>
            <h1 class="header center pink-text">YourKart</h1>
            <div class="row center">
                <h5 class="header col s12 light">YourKart is one stop online shop to meet all your needs!</h5>
            </div>
        </div>
    </div>


    <div class="container">
        <div class="section">
            <div class="row" id="cards-wrapper">
                <!-- product cards go here from jquery -->
            </div>
            <div class="row inactive" style="max-width: 100000px;"id="summary-wrapper">
                Hey, I am summary
                <li>
                    <span>product.name</span>
                    <span>prductc.</span>
                </li>
            </div>
        </div>
    </div>




    <footer class="page-footer indigo">
        <div class="container">
            <div class="row">
                <div class="col l6 s12">
                    <h5 class="white-text">Company Bio</h5>
                    <p class="grey-text text-lighten-4">YourKart is a dummy project that displays some products which you may add into the cart and will show you the cart summary.</p>
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
    <script src="assets/js/materialize.js"></script>
    <script src="assets/js/init.js"></script>

</body>

</html>