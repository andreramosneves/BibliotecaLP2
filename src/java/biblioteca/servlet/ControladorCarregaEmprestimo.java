/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.servlet;

import biblioteca.dao.api.ControllerInterface;
import biblioteca.dao.api.ListarDaoInterface;
import biblioteca.dao.impl.api.relacional.ConexaoInterface;
import biblioteca.dao.impl.relacional.javadb.CatalogoDaoRelacional;
import biblioteca.dao.impl.relacional.javadb.UsuarioDaoRelacional;
import biblioteca.dominio.Livro;
import biblioteca.dominio.Publicacao;
import biblioteca.dominio.Usuario;
import biblioteca.servlets.utils.SessionUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andre
 */
public class ControladorCarregaEmprestimo implements ControllerInterface {
    private HttpSession session;
    private String pagina;
    private HttpServletRequest request;
    
    @Override
    public void init(HttpServletRequest request) {
        session = request.getSession();
        this.request = request;
        pagina = "AdicionarEmprestimo.jsp";

        
        
    }

    @Override
    public void execute() {
        try {
            ConexaoInterface conexao = SessionUtils.getConexao(session);
//            Us dao = SessionUtils.getUsuarioDao(session, conexao);
         

            ListarDaoInterface<Usuario> dao = new UsuarioDaoRelacional(conexao);
            List<Usuario> lista;
            lista = dao.listarTudo();
            request.setAttribute("lista_usuarios", lista);
     
            
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
