

<%@page import="java.util.List"%>
<%@page import="biblioteca.dominio.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciador de usuários</title>
    </head>
 
    <body>
        <h1>Lista de usuários</h1>
        <p><a href="AdicionarUsuario.jsp">Adicionar usuário</a></p>
        <table border="1">
            <tr>
                <td>Nome</td>
                <td>RG</td>
                <td>CPF</td>
                <td> </td>
                <td> </td>
            </tr>
            <c:forEach var="u" items="${lista_usuarios}">
            <tr>
                <td>${u.nome}</td>
                <td>${u.rg}</td>
                <td>${u.cpf}</td>
                <td><a href="ControladorFrente?acao=ControladorEdicaoUsuario&id=${u.idUsuario}">Editar</a></td>
                <td><a href="ControladorFrente?acao=ControladorRemocaoUsuario&id=${u.idUsuario}">Apagar</a></td>
            </tr>
            </c:forEach>
        </table>
        <a href="ListaOpcoes.html">Retornar ao menu</a>
    </body>
</html>
