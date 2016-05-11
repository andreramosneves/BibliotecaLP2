

<%@page import="java.util.List"%>
<%@page import="biblioteca.dominio.Livro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciador de Livro</title>
    </head>
 
    <body>
        <h1>Lista de Livro</h1>
        <p><a href="AdicionarLivro.jsp">Adicionar Livro</a></p>
        <table border="1">
            <tr>
                <td>Nome</td>
                <td>Autor</td>
                <td> </td>
                <td> </td>
            </tr>
            <c:forEach var="u" items="${lista_livros}">
            <tr>

                <td>${u.titulo}</td>
                <td>${u.autor}</td>
                <td><a href="ControladorFrente?acao=ControladorEdicaoLivro&id=${u.numero}">Editar</a></td>
                <td><a href="ControladorFrente?acao=ControladorRemocaoLivro&id=${u.numero}">Apagar</a></td>
            </tr>
            </c:forEach>
        </table>
        <a href="ListaOpcoes.html">Retornar ao menu</a>
    </body>
</html>
