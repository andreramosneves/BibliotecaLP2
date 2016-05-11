/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.apps;

import biblioteca.dao.api.DaoGenerico;
import biblioteca.dao.api.ListarDaoInterface;
import biblioteca.dao.impl.api.relacional.ConexaoInterface;
import biblioteca.dao.impl.relacional.javadb.ConexaoJavaDb;
import biblioteca.dao.impl.relacional.javadb.UsuarioDaoRelacional;
import biblioteca.dominio.Livro;
import biblioteca.dominio.Usuario;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Alterei La La La
 */
public class AppSelectCatalogoComDao {

    public static void apresentarLivros(ListarDaoInterface dao) {
        List<Livro> todosLivros;
        todosLivros = dao.listarTudo();
        for (Livro c : todosLivros) {
            System.out.print("Nro: " + c.getNumero());
            System.out.print(" - ");
            System.out.println("Titulo: " + c.getTitulo());
            System.out.print(" - ");
            System.out.println("Titulo: " + c.getAutor());
        }
    }

    public static void main(String[] args) throws SQLException, ParseException {
        ConexaoInterface conexao = new ConexaoJavaDb("app", 1527, "127.0.0.1", "app", "app");
            DaoGenerico<Usuario> dao = new UsuarioDaoRelacional(conexao);
            Usuario e = new Usuario();
            e.setIdUsuario(2);
            dao.delete(e);
            
            

//        DaoGenerico<Emprestimo> dao = new EmprestimoDaoRelacional(conexao);
//        Emprestimo u = new Emprestimo();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date dataHoje = new Date();
//        u.setDataEntrada(dataHoje);
//        u.setDataRetirada(sdf.parse("2015-11-15"));
//
//        List<ItemEmprestimo> lista = new ArrayList<>();
//        ItemEmprestimo item = new ItemEmprestimo();
//        item.setEmprestimo(u);
//        item.setIdPublicacao(1);
//        lista.add(item);
//
//        Usuario usuario = new Usuario();
//        usuario.setIdUsuario(1);
//        u.setUsuario(usuario);
//
//        u.setItem(lista);
//        u.setValor(20.0);
//
//        dao.create(u);

    }
}
