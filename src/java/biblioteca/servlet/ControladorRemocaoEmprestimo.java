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
import biblioteca.dominio.ItemEmprestimo;
import biblioteca.dominio.Usuario;
import biblioteca.servlets.utils.SessionUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andre
 */
public class ControladorRemocaoEmprestimo implements ControllerInterface {

    private HttpSession session;
    private String pagina;
    private HttpServletRequest request;


    public ControladorRemocaoEmprestimo() {


    }

    @Override
    public void init(HttpServletRequest request) {
        session = request.getSession();
        this.request = request;
        pagina = "ControladorFrente?acao=listarEmprestimo";

    }

    @Override
    public void execute() {
        try {
            ConexaoInterface conexao = SessionUtils.getConexao(session);
//            Us dao = SessionUtils.getUsuarioDao(session, conexao);

            DaoGenerico<Emprestimo> dao = new EmprestimoDaoRelacional(conexao);
           
            Emprestimo u = new Emprestimo();
            u.setIdEmprestimo(Integer.parseInt(request.getParameter("id")));
            u = dao.read(u);
            
            dao.delete(u);
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
