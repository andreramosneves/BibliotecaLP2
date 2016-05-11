

<%@page import="biblioteca.dominio.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adicionar Livro</title>
    </head>
    
    <body>
        <h1>Adicionar Livro</h1>
        <form method="POST" action="ControladorFrente">
            <input type="hidden" name="acao" value="ControladorAdicionarLivro" />
            <p>Nome: <input type="text" name="tx_nome" /></p>
            <p>Autor: <input type="text" name="tx_autor" /></p>
            <p>Ano <input type="number" name="tx_ano" /></p>
            <p><input type="submit" value="Salvar" /></p>
        </form>
    </body>
</html>
