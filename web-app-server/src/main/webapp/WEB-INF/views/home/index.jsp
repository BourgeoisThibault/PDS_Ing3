<%--
  Created by IntelliJ IDEA.
  User: thiba
  Date: 11/10/2017
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../header.jsp"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--main content start-->
<section id="main-content">

    <section class="wrapper">
        <!--overview start-->
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="icon_house_alt"></i> Accueil</h3>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                <a href="/prototype/list">
                <div class="info-box blue-bg">
                    <i class="fa fa-users"></i>
                    <div class="count">Lister</div>
                    <div class="title">Prototype</div>
                </div><!--/.info-box-->
                </a>
            </div><!--/.col-->

            <a href="/">
            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                <a href="/prototype/add">
                <div class="info-box brown-bg">
                    <i class="fa fa-user"></i>
                    <div class="count">Ajouter</div>
                    <div class="title">Prototype</div>
                </div><!--/.info-box-->
                </a>
            </div><!--/.col-->
            </a>

        </div><!--/.row-->


<%@include file="../footer.jsp"%>
