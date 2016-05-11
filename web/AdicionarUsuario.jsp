

<%@page import="biblioteca.dominio.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adicionar usuário</title>
    </head>
    
    <body>
        <h1>Adicionar usuário:</h1>
        <form method="POST" action="ControladorFrente">
            <input type="hidden" name="acao" value="ControladorAdicionarUsuario" />
            <p>Nome: <input type="text" name="tx_nome" /></p>
            <p>CPF: <input type="text" name="tx_cpf" /></p>
            <p>RG: <input type="text" name="tx_rg" /></p>
            <p><input type="submit" value="Salvar" /></p>
        </form>
    </body>
</html>
