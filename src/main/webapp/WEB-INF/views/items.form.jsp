<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<html lang="es">
    <jsp:include page="./fragmentos/head.jsp">
        <jsp:param value="Formulario de Items" name="titulo" />
    </jsp:include>

    <body class="py-5">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-10 col-md-12">
                    <div class="row mb-3 border-bottom">
                        <div class="col-10">
                            <h3>Detalles del Item</h3>
                        </div>

                        <div class="col-2 text-right"></div>
                    </div>

                    <div class="card">
                        <div class="card-body">
                            <form:form modelAttribute="item" method="post">
                                <form:hidden path="id" />

                                <div class="form-group">
                                    <form:label path="descripcion">Descripción</form:label>
                                    <form:textarea path="descripcion" cssClass="form-control" cssErrorClass="form-control is-invalid" rows="4" required="required" />
                                    <form:errors path="descripcion" cssClass="invalid-feedback" />
                                </div>

                                <div class="form-group">
                                    <form:label path="stock">Stock</form:label>
                                    <form:input path="stock" cssClass="form-control" cssErrorClass="form-control is-invalid" />
                                    <form:errors path="stock" cssClass="invalid-feedback" />
                                </div>

                                <div class="form-group">
                                    <form:label path="precioUnitario">Precio Untiario</form:label>
                                    <form:input path="precioUnitario" cssClass="form-control" cssErrorClass="form-control is-invalid" />
                                    <form:errors path="precioUnitario" cssClass="invalid-feedback" />
                                </div>

                                <div class="form-group">
                                    <form:label path="proveedor.id">Proveedor</form:label>
                                    <form:select path="proveedor.id" cssClass="form-control" items="${proveedores}" itemLabel="nombre" itemValue="id" />
                                </div>

                                <div class="form-group text-right mb-0">
                                    <button type="button" class="btn btn-secondary" data-action="cancel">Cancelar</button>
                                    <button type="submit" class="btn btn-primary">Enviar</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="./fragmentos/dependencias.jsp" />
    </body>

</html>
