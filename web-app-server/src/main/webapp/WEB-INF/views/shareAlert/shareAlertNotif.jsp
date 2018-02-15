<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="../header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="row">
        <div class="jumbotron">
            <div class="page-header" style="margin-top:0px;">
                <h2>Bienvenue sur votre messagerie !!!</h2>
            </div>
            <div class="inbox-body">
                <a href="#myModal" data-toggle="modal"  title=""   class="btn btn-primary">
                    <strong>Joindre un client </strong>
                </a>
                <!-- Modal -->
                <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade" style="display: none;">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                <h4 class="modal-title">Nouveau message</h4>
                            </div>
                            <div class="modal-body">
                                <form role="form" method="post" class="form-horizontal" action="#">
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">A</label>
                                        <div class="col-lg-10">
                                            <select name="selectClientId" class="form-control" required>
                                                <c:forEach items="${clientList}" var="clientList" >
                                                    <option value="${clientList.id_client}">
                                                        <c:out value="${clientList.lastName}" />
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Sujet</label>
                                        <div class="col-lg-10">
                                            <select name="selectClientId" class="form-control" >
                                                <option value="">
                                                    <c:out value="Alerte sur le comportement d'un titre" />
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Message</label>
                                        <div class="col-lg-10">
                                            <textarea rows="10" cols="30" class="form-control" id="" name="contenue" placeholder="saisir votre message" required></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-offset-2 col-lg-10">
                                                      <span class="btn green fileinput-button">
                                                        <i class="fa fa-plus fa fa-white"></i>
                                                        <span>Attachment</span>
                                                        <input type="file" name="files[]" multiple="">
                                                      </span>
                                            <button class="btn btn-send" type="submit">Send</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
            </div>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>

