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
                       <strong> Consulter les notifications</strong>
                   </a>
                   <a href="/shareAlert/shareAlertPage" data-toggle="modal"  title=""   class="btn btn-primary" data-fgColor="BLACK" >
                       <strong> Créer une nouvelle notification</strong>
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
                            <th><i class="icon_cogs"></i> Share Name</th>
                            <th><i class="icon_currency"></i>Currency </th>
                            <th><i class="icon_currency"></i>CurrentPrice</th>
                            <th><i class="icon_currency"></i>Change</th>
                            <th><i class="icon-calendar-l"></i>Date</th>
                        </tr>
                        <c:forEach var="list" items="${list}">
                            <tr>
                                <td>${list.name} </td>
                                <td>$${list.currency}</td>
                                <c:forEach var="sharePrice" items="${list.sharePrices}">
                                    <td>$${sharePrice.price}</td>
                                    <td>$${sharePrice.change}</td>
                                    <td>${sharePrice.time}</td>
                                    <fmt:formatDate value="${sharePrice.time}" pattern="MM/dd/yyyy HH:mm"/>
                                    <td><a href="/shareAlert/details">Voir l'historique des variations</a></td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </section>
            </div>
        </div>
<%@include file="../footer.jsp"%>