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
import biblioteca.dao.impl.relacional.javadb.UsuarioDaoRelacional;
import biblioteca.dominio.Emprestimo;
import biblioteca.dominio.Usuario;
import biblioteca.servlets.utils.SessionUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andre
 */
public class ControladorEdicaoEmprestimo implements ControllerInterface {

    private HttpSession session;
    private String pagina;
    private HttpServletRequest request;
    private int id;


    @Override
    public void init(HttpServletRequest request) {
        session = request.getSession();
        this.request = request;
        this.id = Integer.parseInt(request.getParameter("id"));
        pagina = "EditarEmprestimo.jsp";

    }

    @Override
    public void execute() {
        try {
            ConexaoInterface conexao = SessionUtils.getConexao(session);
//            Us dao = SessionUtils.getUsuarioDao(session, conexao);

            DaoGenerico<Emprestimo> dao = new EmprestimoDaoRelacional(conexao);
            Emprestimo u = new Emprestimo();
            u.setIdEmprestimo(id);
            u = dao.read(u);
            DaoGenerico<Usuario> daoU = new UsuarioDaoRelacional(conexao);
            u.setUsuario(daoU.read(u.getUsuario()));
            request.setAttribute("emprestimo", u);

            
            request.setAttribute("lista_itens", u.getItem());
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
