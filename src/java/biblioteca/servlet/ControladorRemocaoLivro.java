/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.servlet;

import biblioteca.dao.api.ControllerInterface;
import biblioteca.dao.api.DaoGenerico;
import biblioteca.dao.impl.api.relacional.ConexaoInterface;
import biblioteca.dao.impl.relacional.javadb.EmprestimoDaoRelacional;
import biblioteca.dao.impl.relacional.javadb.LivroDaoRelacional;
import biblioteca.dao.impl.relacional.javadb.UsuarioDaoRelacional;
import biblioteca.dominio.Emprestimo;
import biblioteca.dominio.ItemEmprestimo;
import biblioteca.dominio.Livro;
import biblioteca.dominio.Usuario;
import biblioteca.servlets.utils.SessionUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andre
 */
public class ControladorRemocaoLivro implements ControllerInterface {

    private HttpSession session;
    private String pagina;
    private HttpServletRequest request;


    public ControladorRemocaoLivro() {


    }

    @Override
    public void init(HttpServletRequest request) {
        session = request.getSession();
        this.request = request;
        pagina = "ControladorFrente?acao=listarLivro";

    }

    @Override
    public void execute() {
        try {
            ConexaoInterface conexao = SessionUtils.getConexao(session);
//            Us dao = SessionUtils.getUsuarioDao(session, conexao);

            DaoGenerico<Livro> dao = new LivroDaoRelacional(conexao);
           
            Livro u = new Livro();
            u.setIdPublicacao(Integer.parseInt(request.getParameter("id")));
            dao.delete(u);
            
;
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
        return ControllerInterface.ReturnType.FORWARD;
    }

}
