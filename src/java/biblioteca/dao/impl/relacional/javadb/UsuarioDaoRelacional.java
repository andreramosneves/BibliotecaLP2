/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao.impl.relacional.javadb;

import biblioteca.dao.api.DaoGenerico;
import biblioteca.dao.api.ListarDaoInterface;
import biblioteca.dao.impl.api.relacional.ConexaoInterface;
import biblioteca.dominio.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDaoRelacional implements DaoGenerico<Usuario>,ListarDaoInterface<Usuario>{

    String sqlDelete = "DELETE FROM usuario WHERE idUsuario = ?";
    String sqlInsert = "INSERT INTO usuario (rg,cpf,nome) VALUES(?,?,?)";
    String sqlUpdate = "UPDATE usuario SET rg = ?, cpf = ?,nome = ? WHERE idUsuario = ?";
    String sqlSelect = "SELECT * FROM usuario WHERE idUsuario = ?";
    String sqlSelectAll = "SELECT * FROM usuario";
    private ConexaoInterface conexao;

    public UsuarioDaoRelacional(ConexaoInterface conexao) {
        this.conexao = conexao;
    }

    @Override
    public void delete(Usuario Objeto) throws SQLException {
        PreparedStatement stm = conexao.getConnection().prepareStatement(sqlDelete);
        stm.setInt(1, Objeto.getIdUsuario());
        stm.execute();
    }

    @Override
    public void create(Usuario objeto) throws SQLException {
        PreparedStatement stm = conexao.getConnection().prepareStatement(sqlInsert);
        stm.setString(1, objeto.getRg());
        stm.setString(2, objeto.getCpf());
        stm.setString(3, objeto.getNome());
        stm.execute();
    }

    @Override
    public Usuario read(Usuario objeto) throws SQLException {
        PreparedStatement stm = conexao.getConnection().prepareStatement(sqlSelect);
        stm.setInt(1, objeto.getIdUsuario());

        ResultSet rst = stm.executeQuery();
        Usuario cliente = null;
        while(rst.next()){
            cliente = new Usuario();
            cliente.setIdUsuario(rst.getInt("idUsuario"));
            cliente.setCpf(rst.getString("cpf"));
            cliente.setNome(rst.getString("nome"));
            cliente.setRg(rst.getString("rg"));
        }
        return cliente;

    }

    @Override
    public void update(Usuario objeto) throws SQLException {
        PreparedStatement stm = conexao.getConnection().prepareStatement(sqlUpdate);
        stm.setString(1, objeto.getRg());
        stm.setString(2, objeto.getCpf());
        stm.setString(3, objeto.getNome());
        stm.setInt(4, objeto.getIdUsuario());

        stm.execute();

        
        
        
    }


    
    @Override
    public List<Usuario> listarTudo() {
        try {
            PreparedStatement stm = conexao.getConnection().prepareStatement(sqlSelectAll);
            
            List<Usuario> lista = new ArrayList<>();
            ResultSet rst = stm.executeQuery();
            Usuario usuario = null;
            while(rst.next()){
                usuario = new Usuario();
                usuario.setIdUsuario(rst.getInt("idUsuario"));
                usuario.setCpf(rst.getString("cpf"));
                usuario.setNome(rst.getString("nome"));
                usuario.setRg(rst.getString("rg"));
                lista.add(usuario);
                
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoRelacional.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
