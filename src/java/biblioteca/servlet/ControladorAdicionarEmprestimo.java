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
import biblioteca.dao.impl.relacional.javadb.UsuarioDaoRelacional;
import biblioteca.dominio.Emprestimo;
import biblioteca.dominio.ItemEmprestimo;
import biblioteca.dominio.Livro;
import biblioteca.dominio.Publicacao;
import biblioteca.dominio.Usuario;
import biblioteca.servlets.utils.SessionUtils;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andre
 */
public class ControladorAdicionarEmprestimo implements ControllerInterface {

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
            Emprestimo u = new Emprestimo();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dt_retirada = request.getParameter("dt_retirar");

            Date dataHoje = new Date();
            u.setDataEntrada(dataHoje);
            u.setDataRetirada(sdf.parse(dt_retirada));

            List<ItemEmprestimo> lista = new ArrayList<>();
            ItemEmprestimo item = new ItemEmprestimo();
            item.setEmprestimo(u);
            item.setIdPublicacao(Integer.parseInt(request.getParameter("id_publicacao")));
            lista.add(item);

            Usuario usuario = new Usuario();
            usuario.setIdUsuario(Integer.parseInt(request.getParameter("id_usuario")));
            u.setUsuario(usuario);

            u.setItem(lista);
            u.setValor(Double.parseDouble(request.getParameter("vl")));

            dao.create(u);
            //Realizar a atualização do DAO Livro ou Publicacao, busco, e se !=nulo
            //Atualiza o nome
            DaoGenerico<ItemEmprestimo> d = new ItemDaoRelacional(conexao);
            d.create(u.getItem().get(0));
            

            
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
