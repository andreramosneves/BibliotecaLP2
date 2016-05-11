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
import biblioteca.dao.impl.relacional.javadb.EmprestimoDaoRelacional;
import biblioteca.dao.impl.relacional.javadb.ItemDaoRelacional;
import biblioteca.dao.impl.relacional.javadb.LivroDaoRelacional;
import biblioteca.dao.impl.relacional.javadb.UsuarioDaoRelacional;
import biblioteca.dominio.Emprestimo;
import biblioteca.dominio.ItemEmprestimo;
import biblioteca.dominio.Livro;
import biblioteca.dominio.Usuario;
import biblioteca.servlets.utils.SessionUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andre
 */
public class ControladorAdicionarItemEmprestimo implements ControllerInterface {

    private HttpSession session;
    private String pagina;
    private HttpServletRequest request;

    @Override
    public void init(HttpServletRequest request) {
        session = request.getSession();
        this.request = request;
        pagina = "ControladorFrente?acao=listarEmprestimo";

        ConexaoInterface conexao = SessionUtils.getConexao(session);
        ListarDaoInterface<Usuario> dao = new UsuarioDaoRelacional(conexao);
        List<Usuario> lista;
        lista = dao.listarTudo();
        request.setAttribute("lista_usuarios", lista);

    }

    @Override
    public void execute() {
        try {
            ConexaoInterface conexao = SessionUtils.getConexao(session);
//            Us dao = SessionUtils.getUsuarioDao(session, conexao);

            DaoGenerico<Emprestimo> dao = new EmprestimoDaoRelacional(conexao);
            DaoGenerico<Livro> daoL = new LivroDaoRelacional(conexao);

            Livro livro = new Livro();
            livro.setIdPublicacao(Integer.parseInt(request.getParameter("id_publicacao")));
            Livro livroBuscado = daoL.read(livro);
            livroBuscado.setIdPublicacao(Integer.parseInt(request.getParameter("id_publicacao")));
            Emprestimo u = new Emprestimo();
            u.setIdEmprestimo(Integer.parseInt(request.getParameter("idEmprestimo")));
            dao.read(u);
            //Realizar a atualização do DAO Livro ou Publicacao, busco, e se !=nulo
            //Atualiza o nome
            DaoGenerico<ItemEmprestimo> d = new ItemDaoRelacional(conexao);
            ItemEmprestimo item = new ItemEmprestimo();
            item.setNome(livroBuscado.getTitulo());
            item.setIdPublicacao(livroBuscado.getIdPublicacao());
            item.setEmprestimo(u);

            d.create(item);

        } catch (Exception ex) {
            pagina = "erro.jsp";
            ex.printStackTrace();
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
