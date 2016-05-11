

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
        <h1>Adicionar Empréstimo</h1>
        <form method="POST" action="ControladorFrente">
            <input type="hidden" name="acao" value="ControladorAdicionarEmprestimo" />
            <label> Usuario:</label>
            <select name="id_usuario">    
                <c:forEach var="elemento" items="${lista_usuarios}">    
                    <option value="${elemento.idUsuario}">${elemento.nome}</option>    
                </c:forEach>    
            </select>            

            <p>Valor: <input type="text" name="vl" /></p>
            <p>Data a Retirar: <input type="date" required name="dt_retirar" /></p>
            <p>Data Emprestimo: <input type="date" disabled name="dt_emprestimo" /></p>

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
