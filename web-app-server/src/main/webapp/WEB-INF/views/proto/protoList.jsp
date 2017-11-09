<%--
  Created by IntelliJ IDEA.
  User: thiba
  Date: 11/10/2017
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../header.jsp"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--main content start-->
<section id="main-content">

    <section class="wrapper">
        <!--overview start-->

        <!--overview start-->
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="fa fa-users"></i> Liste des proto</h3>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <section class="panel">

                    <table class="table table-striped table-advance table-hover">
                        <tbody>
                        <tr>
                            <th><i class="icon_profile"></i> Name</th>
                            <th><i class="icon_mail_alt"></i> Email</th>
                            <th><i class="icon_cogs"></i> Action</th>
                        </tr>

                        <c:forEach var="listValue" items="${list}">
                            <tr>
                                <td>${listValue.name}</td>
                                <td>${listValue.mail}</td>
                                <td>
                                    <div class="btn-group">
                                        <form style="float:left" action="/prototype/edit" method="post">
                                            <input type="hidden" value="${listValue.id}" name="client_id" />
                                            <button class="btn btn-success"><i class="icon_pencil-edit_alt"></i></button>
                                        </form>
                                        <form style="float:left" action="/prototype/deletebyid" method="post">
                                            <input type="hidden" value="${listValue.id}" name="client_id" />
                                            <button class="btn btn-danger"><i class="icon_close_alt2"></i></button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </section>
            </div>
        </div>


<%@include file="../footer.jsp"%>