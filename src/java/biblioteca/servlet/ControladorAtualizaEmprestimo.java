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
import biblioteca.dominio.Emprestimo;
import biblioteca.servlets.utils.SessionUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andre
 */
public class ControladorAtualizaEmprestimo implements ControllerInterface {

    private HttpSession session;
    private String pagina;
    private HttpServletRequest request;

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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dt_retirada = request.getParameter("dt_retirar");
            u.setDataRetirada(sdf.parse(dt_retirada));
            u.setValor(Double.parseDouble(request.getParameter("vl")));
            u.setIdEmprestimo(Integer.parseInt(request.getParameter("idEmprestimo")));
            

            
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
