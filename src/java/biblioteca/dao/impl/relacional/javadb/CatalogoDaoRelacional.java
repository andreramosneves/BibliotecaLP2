/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao.impl.relacional.javadb;

import biblioteca.dao.api.ListarDaoInterface;
import biblioteca.dao.impl.api.relacional.ConexaoInterface;
import biblioteca.dominio.Livro;
import biblioteca.dominio.Publicacao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 2Sem2015
 */
public class CatalogoDaoRelacional implements ListarDaoInterface<Publicacao> {

    private ConexaoInterface conexao;

    public CatalogoDaoRelacional(ConexaoInterface conexao) {
        this.conexao = conexao;
    }

    @Override
    public List<Publicacao> listarTudo() {
        List<Publicacao> livros = new ArrayList<>();
        try {
            Statement st = conexao.getConnection().createStatement();
            String sql = "SELECT idlivro, titulo, autor,idPublicacao FROM livro";
//            StringBuilder str = new StringBuilder();
//            str.append("SELECT\n");
//            str.append("L.titulo,L.autor,REV.editora,REV.titulo\n");
//            str.append("FROM publicacao PUB\n");
//            str.append("LEFT JOIN LIVRO L\n");
//            str.append("ON PUB.idPublicacao = L.idPublicacao\n");
//            str.append("LEFT JOIN REVISTA REV\n");
//            str.append("ON PUB.idPublicacao = REV.idPublicacao\n");
            ResultSet resultados = st.executeQuery(sql);
            while (resultados.next()) {
                
                Publicacao c = new Livro(resultados.getLong("idlivro"), resultados.getString("titulo"), resultados.getString("autor"));
                c.setIdPublicacao(resultados.getInt("idPublicacao"));
                livros.add(c);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
        return livros;
    }

}
