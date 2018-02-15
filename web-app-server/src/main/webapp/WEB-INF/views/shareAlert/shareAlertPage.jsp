<%@include file="../header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="row">
        <div class="jumbotron">
            <div class="page-header" style="margin-top:0px;">
                <h2>Création d'une nouvelle alerte</h2>
            </div>
            <form name="statusAndAlerteSearch" action="" method="" class="form-horizontal">
                <table class="table table-bordered">
                    <tr>
                        <th colspan=3 style="text-align:left;">Paramètres de création d'une nouvelle alerte</th>
                    </tr>
                    <tr>
                        <td style="text-align:center; vertical-align:middle;">Libellé de l'alerte<span class="badge" Title="le libellé de l'alerte est le nom que vous souhaitez donner à votre alerte" style="margin-left:10px;">?</span></td>
                        <td colspan=2 style="text-align:center; vertical-align:middle;">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0" style="width:100%;">
                                <div class="input-group-addon">Abcd</div>
                                <input type="text" name="status_libelle" class="form-control" placeholder="Contenu du libellé..." value="<c:out value=""/>">
                            </div>
                        </td>
                    </tr>
                    <tr>
                    <tr>
                        <td style="text-align:center; vertical-align:middle;">Titre concerné<span class="badge" Title="Correspond au titre" style="margin-left:10px;">?</span></td>
                        <td colspan=2 style="text-align:center; vertical-align:middle;">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0" style="width:100%;">
                                <div class="input-group-addon">Type</div>
                                <select name="shareType" class="form-control" required>
                                    <c:forEach items="${list}" var="list" >
                                        <option value="${list.shareId}">
                                            <c:out value="${list.name}" />
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:center; vertical-align:middle;">Seuil de variation<span class="badge" Title="Correspond aux sorties d'argent mensuels autorisé." style="margin-left:10px;">?</span></td>
                        <td style="text-align:center; vertical-align:middle;">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                <div class="input-group-addon">Euro</div>
                                <input type="number" min="0" name="status_depense_min" class="form-control" placeholder="Intervalle minimum de sortie d'argent..."value="#">
                            </div>
                        </td>
                        <td style="text-align:center; vertical-align:middle;">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                <div class="input-group-addon">Euro</div>
                                <input type="number" min="0" name="status_depense_max" class="form-control" placeholder="Intervalle maximum de sortie d'argent...">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th colspan=3 style="text-align:left;">Paramètres de recherche des alertes</th>
                    </tr>
                    <tr>
                        <td style="text-align:center; vertical-align:middle;">Type de communication<span class="badge" Title="Correspond au type de communication utilisé pour transmettre l'alerte au client." style="margin-left:10px;">?</span></td>
                        <td colspan=2 style="text-align:center; vertical-align:middle;">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0" style="width:100%;">
                                <div class="input-group-addon">Type</div>
                                <select name="selectCommunicationTypeId" class="form-control" required>
                                    <c:forEach items="${communicationTypeList}" var="communicationTypeList" >
                                        <option value="${communicationTypeList.idCommunicationType}">
                                            <c:out value="${communicationTypeList.libelle}" />
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:center; vertical-align:middle;">Fréquence d'alertes<span class="badge" Title="Correspond au nombre de fois qu'un client est alerté." style="margin-left:10px;">?</span></td>
                        <td colspan=2 style="text-align:center; vertical-align:middle;">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0" style="width:100%;">
                                <div class="input-group-addon">Type</div>
                                <input type="number" min="0" name="status_depense_min" class="form-control" placeholder="Intervalle minimum de sortie d'argent..."value="#">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:center; vertical-align:middle;">Condition de durée <span class="badge" Title="Correspond à la condition de déclenchement de l'alerte en fonction de la durée de la condition de liquité." style="margin-left:10px;">?</span></td>
                        <td style="text-align:center; vertical-align:middle;">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                <div class="input-group-addon">Jour</div>
                                <input type="date"  name="alerte_since_min" class="form-control" placeholder="Intervalle minimum de jours..." value="<c:out value=""/>">
                            </div>
                        </td>
                        <td style="text-align:center; vertical-align:middle;">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                <div class="input-group-addon">Jour</div>
                                <input type="date" min="0" name="alerte_since_max" class="form-control" placeholder="Intervalle maximum de jours..." value="">
                            </div>
                        </td>
                    </tr>
                </table>
                <table class="table table-bordered">
                    <tr>
                        <th style="text-align:center; vertical-align:middle;">
                            <button type="submit" class="btn btn-primary">
                                <h5 style="color:white;">
                                    <strong>Créer cette nouvelle alerte</strong>
                                </h5>
                            </button>
                        </th>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<%@include file="../footer.jsp"%>
