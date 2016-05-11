/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.servlet;

import biblioteca.dao.api.ControllerInterface;
import biblioteca.dao.api.DaoGenerico;
import biblioteca.dao.impl.api.relacional.ConexaoInterface;
import biblioteca.dao.impl.relacional.javadb.UsuarioDaoRelacional;
import biblioteca.dominio.Usuario;
import biblioteca.servlets.utils.SessionUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andre
 */
public class ControladorAtualizaUsuario implements ControllerInterface {

    private HttpSession session;
    private String pagina;
    private HttpServletRequest request;

    @Override
    public void init(HttpServletRequest request) {
        session = request.getSession();
        this.request = request;
        pagina = "ControladorFrente?acao=listarUsuario";

    }

    @Override
    public void execute() {
        try {
            ConexaoInterface conexao = SessionUtils.getConexao(session);
//            Us dao = SessionUtils.getUsuarioDao(session, conexao);

            DaoGenerico<Usuario> dao = new UsuarioDaoRelacional(conexao);
            Usuario u = new Usuario();
            u.setCpf((String) request.getParameter("tx_cpf"));
            u.setIdUsuario(Integer.parseInt((String)request.getParameter("tx_id")));
            u.setNome((String) request.getParameter("tx_nome"));
            u.setRg((String) request.getParameter("tx_rg"));
            dao.update(u);
            
        } catch (Exception ex) {
            pagina = "erro.jsp";
        }
    }

    @Override
    public String getReturnPage() {
        return this.pagina;
    }

    @Override
    public ControllerInterface.ReturnType getReturnType() {
        return ControllerInterface.ReturnType.REDIRECT;
    }

}
