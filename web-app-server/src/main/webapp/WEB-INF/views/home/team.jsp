<%--
  Created by IntelliJ IDEA.
  User: thiba
  Date: 11/10/2017
  Time: 22:32
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
                <h3 class="page-header"><i class="fa fa-users"></i> equipe</h3>
            </div>
        </div>

        <div class="row">

            <c:forEach var="listLdapMembers" items="${listLdapMembers}" varStatus="loop">
                <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                    <a href="/member?uid=${listLdapMembers.uid}">
                        <div class="info-box ${loop.index % 2 == 0 ? 'blue' : 'brown'}-bg">
                            <i class="fa fa-user"></i>
                            <div class="count">${listLdapMembers.lastname}</div>
                            <div class="title">${listLdapMembers.firstname} (${listLdapMembers.position})</div>
                        </div><!--/.info-box-->
                    </a>
                </div>
            </c:forEach>

        </div><!--/.row-->

        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="fa fa-users"></i> encadrants</h3>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                <a href="#">
                    <div class="info-box blue-bg">
                        <i class="fa fa-user"></i>
                        <div class="count">BRENNER</div>
                        <div class="title">Alexandre</div>
                    </div><!--/.info-box-->
                </a>
            </div><!--/.col-->

            <a href="/">
                <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                    <a href="#">
                        <div class="info-box brown-bg">
                            <i class="fa fa-user"></i>
                            <div class="count">GIRAUD</div>
                            <div class="title">Gilles</div>
                        </div><!--/.info-box-->
                    </a>
                </div><!--/.col-->
            </a>
            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                <a href="#">
                    <div class="info-box blue-bg">
                        <i class="fa fa-user"></i>
                        <div class="count">MICHEL</div>
                        <div class="title">Olivier</div>
                    </div><!--/.info-box-->
                </a>
            </div><!--/.col-->

        </div><!--/.row-->

        <%@include file="../footer.jsp"%>
