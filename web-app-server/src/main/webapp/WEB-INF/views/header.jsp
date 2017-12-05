<%--
  Created by IntelliJ IDEA.
  User: thiba
  Date: 11/10/2017
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
    <link rel="shortcut icon" href="${contextPath}/resources/All_Icon/favicon.ico">

    <link rel="apple-touch-icon" sizes="57x57" href="${contextPath}/resources/All_Icon/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60" href="${contextPath}/resources/All_Icon/apple-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72" href="${contextPath}/resources/All_Icon/apple-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76" href="${contextPath}/resources/All_Icon/apple-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114" href="${contextPath}/resources/All_Icon/apple-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120" href="${contextPath}/resources/All_Icon/apple-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144" href="${contextPath}/resources/All_Icon/apple-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152" href="${contextPath}/resources/All_Icon/apple-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180" href="${contextPath}/resources/All_Icon/apple-icon-180x180.png">
    <link rel="icon" type="image/png" sizes="192x192"  href="${contextPath}/resources/All_Icon/android-icon-192x192.png">
    <link rel="icon" type="image/png" sizes="32x32" href="${contextPath}/resources/All_Icon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96" href="${contextPath}/resources/All_Icon/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16" href="${contextPath}/resources/All_Icon/favicon-16x16.png">
    <link rel="manifest" href="${contextPath}/resources/All_Icon/manifest.json">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="${contextPath}/resources/All_Icon/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">

    <title>Esibank</title>

    <!-- Bootstrap CSS -->
    <link href="${contextPath}/resources/template/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="${contextPath}/resources/template/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="${contextPath}/resources/template/css/elegant-icons-style.css" rel="stylesheet" />
    <link href="${contextPath}/resources/template/css/font-awesome.min.css" rel="stylesheet" />
    <!-- full calendar css-->
    <link href="${contextPath}/resources/template/assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />
    <link href="${contextPath}/resources/template/assets/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" />
    <!-- easy pie chart-->
    <link href="${contextPath}/resources/template/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
    <!-- owl carousel -->
    <link rel="stylesheet" href="${contextPath}/resources/template/css/owl.carousel.css" type="text/css">
    <link href="${contextPath}/resources/template/css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
    <!-- Custom styles -->
    <link rel="stylesheet" href="${contextPath}/resources/template/css/fullcalendar.css">
    <link href="${contextPath}/resources/template/css/widgets.css" rel="stylesheet">
    <link href="${contextPath}/resources/template/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/template/css/style-responsive.css" rel="stylesheet" />
    <link href="${contextPath}/resources/template/css/xcharts.min.css" rel=" stylesheet">
    <link href="${contextPath}/resources/template/css/jquery-ui-1.10.4.min.css" rel="stylesheet">
    <!-- =======================================================
        Theme Name: NiceAdmin
        Theme URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
        Author: BootstrapMade
        Author URL: https://bootstrapmade.com
    ======================================================= -->
</head>

<body>
<!-- container section start -->
<section id="container" class="">


    <header class="header dark-bg">
        <div class="toggle-nav">
            <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"><i class="icon_menu"></i></div>
        </div>

        <!--logo start-->
        <a href="/" class="logo">Esi <span class="lite">Bank</span></a>
        <!--logo end-->

        <!--
        <div class="nav search-row" id="top_menu">
            <ul class="nav top-menu">
                <li>
                    <form class="navbar-form">
                        <input class="form-control" placeholder="Search" type="text">
                    </form>
                </li>
            </ul>
        </div>
        -->

        <div style="visibility: hidden" class="top-nav notification-row">
            <!-- notificatoin dropdown start-->
            <ul class="nav pull-right top-menu">
                <!-- alert notification end-->
                <!-- user login dropdown start-->
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="profile-ava">
                                <img alt="" src="#">
                            </span>
                        <span class="username">Administrator</span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu extended logout">
                        <div class="log-arrow-up"></div>
                        <li class="eborder-top">
                            <a href="#"><i class="icon_profile"></i> My Profile</a>
                        </li>
                        <li>
                            <a href="#"><i class="icon_mail_alt"></i> My Inbox</a>
                        </li>
                        <li>
                            <a href="login.html"><i class="icon_key_alt"></i> Log Out</a>
                        </li>
                        <li>
                            <a href="documentation.html"><i class="icon_key_alt"></i> Documentation</a>
                        </li>
                    </ul>
                </li>
                <!-- user login dropdown end -->
            </ul>
            <!-- notificatoin dropdown end-->
        </div>
    </header>
    <!--header end-->

    <!--sidebar start-->
    <aside>
        <div id="sidebar"  class="nav-collapse ">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu">
                <li>
                    <a class="" href="/">
                        <i class="icon_house_alt"></i>
                        <span>Accueil</span>
                    </a>
                </li>
                <li>
                    <a class="" href="/team">
                        <i class="fa fa-users"></i>
                        <span>Equipe</span>
                    </a>
                </li>
                <li>
                    <a class="" href="/notification">
                        <i class="fa fa-paper-plane"></i>
                        <span>Notification</span>
                    </a>
                </li>
                <li>
                    <a class="" href="http://file.esibank.inside.esiag.info/">
                        <i class="icon_download"></i>
                        <span>Android Application</span>
                    </a>
                </li>
                <li>
                    <a class="" href="/about">
                        <i class="icon_info_alt"></i>
                        <span>A propos</span>
                    </a>
                </li>
            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>
    <!--sidebar end-->