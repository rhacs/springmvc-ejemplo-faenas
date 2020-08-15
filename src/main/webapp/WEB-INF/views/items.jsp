<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="es">
    <jsp:include page="./fragmentos/head.jsp">
        <jsp:param value="Listado de Items" name="titulo" />
    </jsp:include>

    <body class="py-5">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-10 col-md-12">
                    <div class="row mb-3 border-bottom">
                        <div class="col-10">
                            <h3>Listado de Items</h3>
                        </div>

                        <div class="col-2 text-right">
                            <button type="button" class="btn btn-sm btn-primary" data-action="add" data-member="items">
                                <i class="fas fa-plus-square fa-fw"></i>
                            </button>
                        </div>
                    </div>

                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <caption class="text-right">Cantidad de registros: ${items != null ? items.size() : '0'}</caption>

                            <thead>
                                <tr>
                                    <th scope="col" class="text-nowrap">#</th>
                                    <th scope="col" class="text-nowrap">Descripción</th>
                                    <th scope="col" class="text-nowrap text-right">Stock</th>
                                    <th scope="col" class="text-nowrap text-right">Valor</th>
                                </tr>
                            </thead>

                            <tbody>
                                <core:choose>
                                    <core:when test="${items != null && items.size() > 0}">
                                        <core:forEach items="${items}" var="item">
                                        <tr>
                                            <th scope="row" class="text-nowrap">${item.getId()}</th>
                                            <td>${item.getDescripcion()}</td>
                                            <td class="text-nowrap text-right">${item.getStock()}</td>
                                            <td class="text-nowrap text-right"><format:formatNumber type="currency" currencyCode="CLP" value="${item.getPrecioUnitario()}" />
                                        </tr>
                                        </core:forEach>
                                    </core:when>
                                    <core:otherwise>
                                        <tr>
                                            <th scope="row" class="text-center" colspan="4">No hay registros</th>
                                        </tr>
                                    </core:otherwise>
                                </core:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="./fragmentos/dependencias.jsp" />
    </body>

</html>
