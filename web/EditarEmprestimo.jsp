

<%@page import="biblioteca.dominio.ItemEmprestimo"%>
<%@page import="biblioteca.dominio.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adicionar Emprestimo</title>
    </head>

    <body>
        <h1>Editar Empréstimo</h1>
        <form method="POST" action="ControladorFrente">
            <input type="hidden" name="acao" value="ControladorAtualizaEmprestimo" />
            <input type="hidden" name="idEmprestimo" value="${emprestimo.idEmprestimo}" />
            <label> Usuario: ${emprestimo.getUsuario().getNome()} </label>             

            <p>Valor: <input type="text" name="vl" value="${emprestimo.valor}" /></p>
            <p>Data a Retirar: <input type="date" value="${emprestimo.getDataRetirada()}" required name="dt_retirar" /></p>
            <p>Data Emprestimo: </p>

            <a href="ControladorFrente?acao=ControladorCarregaItemEmprestimo&idEmprestimo=${emprestimo.idEmprestimo}"> Inserir Item</a>
                <table border="1">
                    <tr>
                        <td>Codigo Publicacao</td>
                        <td>Descricao</td>
                    </tr>
            <c:forEach var="u" items="${lista_itens}">
                    <tr>
                        <td>${u.idPublicacao}</td>
                        <td>${u.nome}</td>
                        <td><a href="ControladorFrente?acao=ControladorRemocaoItem&id=${u.idItem}">Apagar Item</a></td>
                    </tr>
            </c:forEach>
                </table>

            <p><input type="submit" value="Salvar" /></p>
        </form>
    </body>
</html>
