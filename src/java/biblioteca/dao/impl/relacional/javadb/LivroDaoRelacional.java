 
package biblioteca.dao.impl.relacional.javadb;

import biblioteca.dao.api.DaoGenerico;
import biblioteca.dao.impl.api.relacional.ConexaoInterface;
import biblioteca.dominio.Livro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

 
public class LivroDaoRelacional implements DaoGenerico<Livro>{
//INSERT INTO livro(autor,idPublicacao,isbn,titulo,ano,valor) VALUES('Lev Psakhis',1,321312,'Advanced Chess Tactics',2009,5.00);
    
    private String sqlDelete = "DELETE FROM LIVRO WHERE idPublicacao = ?";
    private String sqlDeletePublicacao = "DELETE FROM PUBLICACAO WHERE idPublicacao = ?";
    private String sqlInsert = "INSERT INTO livro(titulo,ano,autor,idPublicacao) VALUES (?,?,?,?)";
    private String sqlInsertPublicacao = "INSERT INTO publicacao(datapublicacao,idCategoria) VALUES (?,1)";
    private String sqlSelect = "SELECT * FROM livro WHERE idPublicacao = ?";
    private ConexaoInterface conexao;

    public LivroDaoRelacional(ConexaoInterface conexao) {
        this.conexao = conexao;
    }
    
    @Override
    public void delete(Livro Objeto) throws SQLException  {
   
        PreparedStatement stm = conexao.getConnection().prepareStatement(sqlDelete);
        stm.setInt(1, Objeto.getIdPublicacao());
        stm.execute();
        stm = conexao.getConnection().prepareStatement(sqlDeletePublicacao);
        stm.setInt(1, Objeto.getIdPublicacao());
        stm.execute();

    
    }

    @Override
    public void create(Livro objeto) throws SQLException {
        PreparedStatement stm = conexao.getConnection().prepareStatement(sqlInsertPublicacao, Statement.RETURN_GENERATED_KEYS);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        stm.setDate(1, new java.sql.Date(new Date().getTime()));
        stm.execute();
        ResultSet rst = stm.getGeneratedKeys();
        while (rst.next()) {
            objeto.setIdPublicacao(rst.getInt(1));
        }
        
        
        
        stm = conexao.getConnection().prepareStatement(sqlInsert);
        stm.setString(1, objeto.getTitulo());
        stm.setInt(2, objeto.getAnoPublicacao());
        stm.setString(3, objeto.getAutor());
        stm.setInt(4,objeto.getIdPublicacao());
        stm.execute();
        

    }

    @Override
    public Livro read(Livro objeto) throws SQLException {
        PreparedStatement stm = conexao.getConnection().prepareStatement(sqlSelect);
        stm.setInt(1, objeto.getIdPublicacao());
        ResultSet rst = stm.executeQuery();
        Livro livro = null;
        while(rst.next()){
            livro = new Livro();
            livro.setAnoPublicacao(rst.getInt("ano"));
            livro.setAutor(rst.getString("autor"));
            livro.setTitulo(rst.getString("titulo"));
            livro.setNumero(rst.getInt("idLivro"));
        }
        return livro;

    }

    @Override
    public void update(Livro objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
