<%--
  Created by IntelliJ IDEA.
  User: thiba
  Date: 25/10/2017
  Time: 16:07
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
                <h3 class="page-header"><i class="fa fa-user"></i> Membre ${member.uid}</h3>
            </div>
        </div>

        <div class="col-lg-12">
            <div class="profile-widget profile-widget-info">
                <div class="panel-body">
                    <div class="col-lg-2 col-sm-2">
                        <h4>${member.firstname} ${member.lastname}</h4>
                        <div class="follow-ava">
                            <img src="/getldapimageuser?uid=${member.uid}">
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-4 follow-info">
                        <p>Voici les informations du serveur LDAP.</p>
                        <p><span><i class="icon_mail"></i> ${member.mail}</span></p>
                        <p><span><i class="icon_comment"></i> ${member.description}</span></p>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel-body">
            <div class="tab-content">
                <!-- profile -->
                <div id="profile" class="tab-pane active">
                    <section class="panel">
                        <div class="bio-graph-heading">
                            Informations LDAP
                        </div>
                        <div class="panel-body bio-graph-info">
                            <h1>Informations</h1>
                            <div class="row">
                                <div class="bio-row">
                                    <p><span>First Name </span>: ${member.firstname} </p>
                                </div>
                                <div class="bio-row">
                                    <p><span>Last Name </span>: ${member.lastname} </p>
                                </div>
                                <div class="bio-row">
                                    <p><span>Position </span>: ${member.position} </p>
                                </div>
                                <div class="bio-row">
                                    <p><span>Company </span>: ${member.company} </p>
                                </div>
                                <div class="bio-row">
                                    <p><span>Login </span>: ${member.uid} </p>
                                </div>
                                <div class="bio-row">
                                    <p><span>Email </span>: ${member.mail} </p>
                                </div>
                                <div class="bio-row">
                                    <p><span>Description </span>: ${member.description} </p>
                                </div>
                                <div class="bio-row">
                                    <p><span>Phone </span>:  ${member.phone} </p>
                                </div>
                            </div>
                        </div>
                    </section>
                    <section>
                        <div class="row">
                        </div>
                    </section>
                </div>
            </div>
        </div>

        <%@include file="../footer.jsp"%>

