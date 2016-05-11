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
import biblioteca.dominio.Livro;
import biblioteca.dominio.Publicacao;
import biblioteca.dominio.Revista;
import biblioteca.servlets.utils.SessionUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andre
 */
public class ControladorListaPublicacao implements ControllerInterface {
    private HttpSession session;
    private String pagina;
    private HttpServletRequest request;
    
    @Override
    public void init(HttpServletRequest request) {
        session = request.getSession();
        this.request = request;
        pagina = "ListaLivro.jsp";

        
        
    }

    @Override
    public void execute() {
        try {
            ConexaoInterface conexao = SessionUtils.getConexao(session);
//            Us dao = SessionUtils.getUsuarioDao(session, conexao);
         

            ListarDaoInterface<Publicacao> dao = new CatalogoDaoRelacional(conexao);
            List<Publicacao> lista = dao.listarTudo();
            for (Publicacao lista1 : lista) {
                
            }

//            lista = dao.listarTudo();
            request.setAttribute("lista_livros", lista);
   
//            ListarDaoInterface<Revista> dao = new CatalogoDaoRelacional(conexao);
//            List<Revista> lis;
//            lista = dao.listarTudo();
//            request.setAttribute("lista_revistas", lis);
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
