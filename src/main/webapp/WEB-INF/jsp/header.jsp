<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en" style="position: relative; min-height: 100%">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <link rel='stylesheet' type="text/css" href="../css/Styles.css">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="/welcome"><img src="../images/logo.png"></a>
    <div class="navbar-collapse collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto" >
                <li class="nav-item">
                    <a class="nav-link" href="/menu/soups">Soups</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/menu/meals">Meals</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/menu/drinks">Drinks</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/menu/deserts">Deserts</a>
                </li>
                <li>
                    <button class="btn btn-success nav-button" style="margin-right:10px;">
                        <span class="left-span"> Call a waiter  <i class="fa fa-bell" aria-hidden="true"></i></span>
                    </button>
                </li>
                <li>

                    <c:if test="${not empty orderMap}">
                        <a href="/order">
                            <button class="btn btn-info nav-button" style="margin-right:10px;">
                                <span class="left-span">Order  <i class="fa fa-coffee" aria-hidden="true"></i></span>
                             </button>
                        </a>
                    </c:if>
                    <c:if test="${empty orderMap}">
                            <button class="btn btn-info nav-button" disabled style="margin-right:10px;">
                                <span class="left-span">Order  <i class="fa fa-coffee" aria-hidden="true"></i></span>
                            </button>
                    </c:if>

                </li>
                <li class="nav-item">
                    <form  action="#" class="nav-form">
                        <input class="form-control mr-sm-2" type="text" placeholder="Search">
                    </form>
                </li>
                <li class="nav-item">
                    <button type="button" class="btn btn-default btn-lg nav-button">
                        <i class="fa fa-search"></i>
                    </button>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Log in <i class="fa fa-user" aria-hidden="true"></i></a>
                </li>
            </ul>
        </div>
</nav>
