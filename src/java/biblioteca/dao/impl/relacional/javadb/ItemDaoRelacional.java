/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao.impl.relacional.javadb;

import biblioteca.dao.api.DaoGenerico;
import biblioteca.dao.impl.api.relacional.ConexaoInterface;
import biblioteca.dominio.ItemEmprestimo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Andre
 */
public class ItemDaoRelacional implements DaoGenerico<ItemEmprestimo> {

    private String sqlSelect = "SELECT * FROM ITEMEMPRESTIMO WHERE idEmprestimo = ?";
    private String sqlInsert = "INSERT INTO ITEMEMPRESTIMO(idPublicacao,idEmprestimo,nome)"
            + "VALUES(?,?,?)";
    private String sqlDelete = "DELETE FROM ITEMEMPRESTIMO WHERE idItem = ?";
    private ConexaoInterface conexao;

    public ItemDaoRelacional(ConexaoInterface conexao) {
        this.conexao = conexao;
    }

    @Override
    public void delete(ItemEmprestimo Objeto) throws SQLException {
      PreparedStatement stm = conexao.getConnection().prepareStatement(sqlDelete);
      stm.setInt(1, Objeto.getIdItem());
      stm.execute();
      
 
    }

    @Override
    public void create(ItemEmprestimo objeto) throws SQLException {
        PreparedStatement stm = conexao.getConnection().prepareStatement(sqlInsert);
        stm.setInt(1, objeto.getIdPublicacao());
        stm.setInt(2, objeto.getEmprestimo().getIdEmprestimo());
        stm.setString(3, objeto.getNome());

        stm.execute();

    }

    @Override
    public ItemEmprestimo read(ItemEmprestimo objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ItemEmprestimo objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
