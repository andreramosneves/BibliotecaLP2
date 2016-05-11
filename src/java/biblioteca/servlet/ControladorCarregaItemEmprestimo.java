        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.servlet;

import biblioteca.dao.api.ControllerInterface;
import biblioteca.dao.api.DaoGenerico;
import biblioteca.dao.api.ListarDaoInterface;
import biblioteca.dao.impl.api.relacional.ConexaoInterface;
import biblioteca.dao.impl.relacional.javadb.CatalogoDaoRelacional;
import biblioteca.dao.impl.relacional.javadb.EmprestimoDaoRelacional;
import biblioteca.dominio.Emprestimo;
import biblioteca.dominio.Livro;
import biblioteca.dominio.Publicacao;
import biblioteca.servlets.utils.SessionUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andre
 */
public class ControladorCarregaItemEmprestimo implements ControllerInterface {
    private HttpSession session;
    private String pagina;
    private HttpServletRequest request;
    
    @Override
    public void init(HttpServletRequest request) {
        session = request.getSession();
        this.request = request;
        pagina = "AdicionarItem.jsp";
    }

    @Override
    public void execute() {
        try {
            ConexaoInterface conexao = SessionUtils.getConexao(session);
            DaoGenerico<Emprestimo> dao = new EmprestimoDaoRelacional(conexao);
            Emprestimo e = new Emprestimo();
            e.setIdEmprestimo(Integer.parseInt(request.getParameter("idEmprestimo")));
            e = dao.read(e);
            request.setAttribute("emprestimo",e);

            ListarDaoInterface<Publicacao> daoL = new CatalogoDaoRelacional(conexao);
            List<Publicacao> listaL;
            listaL = daoL.listarTudo();
            request.setAttribute("lista_livros", listaL);

//            ListarDaoInterface<Revista> daoR = new RevistaDaoRelacional(conexao);
//            List<Revista> listaR;
//            listaR = daoR.listarTudo();
//            request.setAttribute("lista_revistas", listaR);
            
            
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
