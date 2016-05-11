

<%@page import="java.util.List"%>
<%@page import="biblioteca.dominio.Usuario"%>
<%@page import="biblioteca.dominio.Emprestimo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciador de Empréstimos</title>
    </head>
 
    <body>
        <h1>Lista de Empréstimos</h1>
        <p><a href="ControladorFrente?acao=ControladorCarregaEmprestimo">Realizar Empréstimo</a></p>
        
        
        <table border="1">
            <tr>
                <td>Usuario</td>
                <td>Valor</td>
                <td>Qt.Itens Emprestados</td>
                <td>Data Emprestimo</td>
                <td>Data a Retirar</td>
            </tr>
            <c:forEach var="u" items="${lista_emprestimos}">
            <tr>
                <td>${u.usuario.nome}</td>
                <td>${u.valor}</td>
                <td>${u.getItem().size()}</td>
                <td>${u.dataEntrada}</td>
                <td>${u.dataRetirada}</td>
                <td><a href="ControladorFrente?acao=ControladorEdicaoEmprestimo&id=${u.idEmprestimo}">Editar</a></td>
                <td><a href="ControladorFrente?acao=ControladorRemocaoEmprestimo&id=${u.idEmprestimo}">Apagar</a></td>
            </tr>
            </c:forEach>
        </table>
        <a href="ListaOpcoes.html">Retornar ao menu</a>
    </body>
</html>
