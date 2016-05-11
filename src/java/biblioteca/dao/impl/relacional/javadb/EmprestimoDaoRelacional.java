/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao.impl.relacional.javadb;

import biblioteca.dao.api.DaoGenerico;
import biblioteca.dao.api.ListarDaoInterface;
import biblioteca.dao.impl.api.relacional.ConexaoInterface;
import biblioteca.dominio.Emprestimo;
import biblioteca.dominio.ItemEmprestimo;
import biblioteca.dominio.Usuario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmprestimoDaoRelacional implements DaoGenerico<Emprestimo>, ListarDaoInterface<Emprestimo> {

    String sqlDelete = "DELETE FROM emprestimo WHERE idEmprestimo = ?";
    String sqlDeleteItens = "DELETE FROM itememprestimo WHERE idEmprestimo = ?";
    String sqlInsert = "INSERT INTO emprestimo (idUsuario,dataRetirada,dataEntrada,prazoDias,valor)" + ""
            + " VALUES(?,?,?,?,?)";
    String sqlUpdate = "UPDATE emprestimo SET dataRetirada = ?, valor = ? WHERE idEmprestimo = ?";
    String sqlSelect = "SELECT * FROM emprestimo WHERE idEmprestimo = ?";
    String sqlSelectItens = "SELECT * FROM itememprestimo WHERE idEmprestimo = ?";
    String sqlSelectAll = "SELECT * FROM emprestimo";

    private ConexaoInterface conexao;

    public EmprestimoDaoRelacional(ConexaoInterface conexao) {
        this.conexao = conexao;
    }

    @Override
    public void delete(Emprestimo Objeto) throws SQLException {
        PreparedStatement stm = conexao.getConnection().prepareStatement(sqlDeleteItens);
        stm.setInt(1, Objeto.getIdEmprestimo());
        stm.execute();

        
        stm = conexao.getConnection().prepareStatement(sqlDelete);
        stm.setInt(1, Objeto.getIdEmprestimo());
        stm.execute();

    }

    @Override
    public void create(Emprestimo objeto) throws SQLException {
        PreparedStatement stm = conexao.getConnection().prepareStatement(sqlInsert,
                Statement.RETURN_GENERATED_KEYS);
        stm.setInt(1, objeto.getUsuario().getIdUsuario());
        stm.setDate(2, new Date(objeto.getDataRetirada().getTime()));
        stm.setDate(3, new Date(objeto.getDataEntrada().getTime()));
        stm.setInt(4, objeto.getDias());
        stm.setDouble(5, objeto.getValor());
        stm.execute();
        ResultSet rst = stm.getGeneratedKeys();
        while (rst.next()) {
            objeto.setIdEmprestimo(rst.getInt(1));
        }

    }

    @Override
    public Emprestimo read(Emprestimo objeto) throws SQLException {
        PreparedStatement stm = conexao.getConnection().prepareStatement(sqlSelect);
        stm.setInt(1, objeto.getIdEmprestimo());

        ResultSet rst = stm.executeQuery();
        Emprestimo emprestimo = null;
        while (rst.next()) {
            emprestimo = new Emprestimo();
            emprestimo.setIdEmprestimo(rst.getInt("idEmprestimo"));
            emprestimo.setDataEntrada(rst.getDate("dataEntrada"));
            emprestimo.setDataRetirada(rst.getDate("dataRetirada"));
            emprestimo.setDias(rst.getInt("prazoDias"));
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rst.getInt("idUsuario"));
            emprestimo.setUsuario(usuario);
            emprestimo.setRecibo(null);
            emprestimo.setValor(rst.getDouble("valor"));

            PreparedStatement stm2 = conexao.getConnection().prepareStatement(sqlSelectItens);
            stm2.setInt(1, objeto.getIdEmprestimo());
            ResultSet rst2 = stm2.executeQuery();
            ItemEmprestimo item;
            while (rst2.next()) {
                item = new ItemEmprestimo();
                item.setIdPublicacao(rst2.getInt("idPublicacao"));
                item.setEmprestimo(emprestimo);
                item.setNome(rst2.getString("nome"));
                item.setIdItem(rst2.getInt("idItem"));
                emprestimo.getItem().add(item);
            }

        }
        return emprestimo;

    }

    @Override
    public void update(Emprestimo objeto) throws SQLException {
        PreparedStatement stm = conexao.getConnection().prepareStatement(sqlUpdate);
        stm.setDate(1, new Date(objeto.getDataRetirada().getTime()));
        stm.setDouble(2, objeto.getValor());
        stm.setInt(3, objeto.getIdEmprestimo());

        stm.execute();

    }

    @Override
    public List<Emprestimo> listarTudo() {
        try {
            PreparedStatement stm = conexao.getConnection().prepareStatement(sqlSelectAll);

            ResultSet rst = stm.executeQuery();
            Emprestimo emprestimo = null;
            List<Emprestimo> lista = new ArrayList<>();
            while (rst.next()) {
                emprestimo = new Emprestimo();
                emprestimo.setIdEmprestimo(rst.getInt("idEmprestimo"));
                emprestimo.setDataEntrada(rst.getDate("dataEntrada"));
                emprestimo.setDataRetirada(rst.getDate("dataRetirada"));
                emprestimo.setDias(rst.getInt("prazoDias"));
                emprestimo.setItem(null);
                emprestimo.setRecibo(null);
                emprestimo.setValor(rst.getDouble("valor"));
                lista.add(emprestimo);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimoDaoRelacional.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
