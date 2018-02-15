<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="../header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--main content start-->
<section id="main-content">
    <section class="wrapper">
        <!--overview start-->
        <!--overview start-->
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="" data-bgColor="BLACK"></i> Liste des titres en bourse</h3>
               <div>
                   <a href="/shareAlert/notifClient" data-toggle="modal"  title=""   class="btn btn-primary" data-fgColor="BLACK" >
                       <strong> Notification du client </strong>
                   </a>
              </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <section class="panel">
                    <table class="table table-striped table-advance table-hover">
                        <tbody>
                        <tr>
                            <th>Id</th>
                            <th>idClient </th>
                            <th>Message</th>
                        </tr>
                        <c:forEach var="list" items="${list}">
                            <tr>
                                <td>${list.idClientNotif} </td>
                                <td>${list.idClient}</td>
                                <td>${list.alerteMessage}</td>
                                <td><input type="checkbox" name="souscribe"></input></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </section>
            </div>
        </div>
<%@include file="../footer.jsp"%>