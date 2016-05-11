

<%@page import="biblioteca.dominio.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar informações do usuário</title>
    </head>
    
    <body>
        <h1>Edição de usuário:</h1>
        <form method="POST" action="ControladorFrente">
            <p>Identificação: ${Usuario.idUsuario}</p>
            <input type="hidden" name="acao" value="ControladorAtualizaUsuario" />
            <input type="hidden" name="tx_id" value="${Usuario.idUsuario}" />
            <p>Nome: <input type="text" name="tx_nome" value="${Usuario.nome}" /></p>
            <p>CPF: <input type="text" name="tx_cpf" value="${Usuario.cpf}" /></p>
            <p>RG: <input type="text" name="tx_rg" value="${Usuario.rg}" /></p>
            <p><input type="submit" value="Salvar" /></p>
        </form>
    </body>
</html>
