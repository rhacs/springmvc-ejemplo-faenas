<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <!-- Codificación de Caracteres -->
        <meta charset="UTF-8" />

        <!-- Configuración de ancho y escala inicial para todos los dispositivos -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- Título de la página -->
        <title><core:out default="Información de Faenas" value="${param.titulo}" /></title>

        <!-- Hojas de estilo -->

        <!-- Bootstrap -->
        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.min.css"
            integrity="sha512-MoRNloxbStBcD8z3M/2BmnT+rg4IsMxPkXaGh2zD6LGNNFE80W3onsAhRcMAMrSoyWL9xD7Ert0men7vR8LUZg=="
            crossorigin="anonymous" />

        <!-- FontAwesome -->
        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
            integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog=="
            crossorigin="anonymous" />
    </head>
