<%--
  Created by IntelliJ IDEA.
  User: thiba
  Date: 21/11/2017
  Time: 19:39
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
                <h3 class="page-header"><i class="icon_house_alt"></i> Test envoie de notification</h3>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <section class="panel">
                    <div class="panel-body">
                        <form class="form-horizontal" method="post" action="/notification">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Uid</label>
                                <div class="col-sm-10">
                                    <input name="uid" type="text" class="form-control round-input">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Titre</label>
                                <div class="col-sm-10">
                                    <input name="title" type="text" class="form-control round-input">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Message</label>
                                <div class="col-sm-10">
                                    <input name="message" type="text" class="form-control round-input">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Cible</label>
                                <div class="col-sm-10">
                                    <input name="target" type="text" class="form-control round-input">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-2 col-lg-10">
                                    <button class="btn btn-info" type="submit">Envoyer</button>
                                    <c:if test="${accept = true}">
                                        <span class="badge bg-primary"><c:out value = "${returnmessage}"/></span>
                                    </c:if>
                                </div>
                            </div>
                        </form>
                    </div>
                </section>
            </div>
        </div>
        <!-- Basic Forms & Horizontal Forms-->


        <%@include file="../footer.jsp"%>
