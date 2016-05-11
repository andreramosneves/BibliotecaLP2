

<%@page import="biblioteca.dominio.Emprestimo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adicionar Item Emprestimo</title>
    </head>

    <body>
        <h1>Adicionar Item Empréstimo do código ${emprestimo.idEmprestimo}</h1>
        <form method="POST" action="ControladorFrente">
            <input type="hidden" name="acao" value="ControladorAdicionarItemEmprestimo" />
            <input type="hidden" name="idEmprestimo" value="${emprestimo.idEmprestimo}" />

            
            <label>Livros/Revistas:</label>
            <select name="id_publicacao">
                <c:forEach var="e" items="${lista_livros}">    
                    <option value="${e.idPublicacao}">${e.titulo}</option>    
                </c:forEach>    
                <c:forEach var="r" items="${lista_revistas}">    
                    <option value="${r.idPublicacao}">${r.titulo}</option>    
                </c:forEach>    
                    
            </select>
            <p><input type="submit" value="Salvar" /></p>
        </form>
    </body>
</html>
