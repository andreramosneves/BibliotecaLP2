/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.servlets.utils;


import biblioteca.dao.api.DaoGenerico;
import biblioteca.dao.impl.api.relacional.ConexaoInterface;
import biblioteca.dao.impl.relacional.javadb.UsuarioDaoRelacional;
import biblioteca.dao.impl.relacional.javadb.ConexaoJavaDb;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static ConexaoInterface getConexao(HttpSession session) {
        ConexaoInterface conexao;
        conexao = (ConexaoInterface) session.getAttribute("conexaoSessao");
        if (conexao == null) {
            conexao = new ConexaoJavaDb("app", 1527,"127.0.0.1", "app", "app");   
            session.setAttribute("conexaoSessao", conexao);
        }
        return conexao;
    }

    public static DaoGenerico getUsuarioDao(HttpSession session, ConexaoInterface conexao)  {
        DaoGenerico dao;
        dao = (DaoGenerico) session.getAttribute("usuarioDaoSessao");
        if (dao == null) {
            dao = new UsuarioDaoRelacional(conexao);
            session.setAttribute("usuarioDaoSessao", dao);
        }
        return dao;
    }

}
