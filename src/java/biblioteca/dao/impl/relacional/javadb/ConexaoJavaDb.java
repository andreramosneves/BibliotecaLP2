/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao.impl.relacional.javadb;

import biblioteca.dao.impl.api.relacional.ConexaoInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 41445368
 */
public class ConexaoJavaDb implements ConexaoInterface {

    private Connection conexao;
    private final String nomeBancoDados;
    private final int porta;
    private final String hostName;
    private final String senha;
    private final String usuario;

    public ConexaoJavaDb(String nomeBancoDados, int porta, String hostName, String senha, String usuario) {
        this.nomeBancoDados = nomeBancoDados;
        this.porta = porta;
        this.hostName = hostName;
        this.senha = senha;
        this.usuario = usuario;
    }

    @Override
    public Connection getConnection() {
        if (conexao == null) {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                String url = "jdbc:derby://" + hostName + ":" + porta + "/" + nomeBancoDados;
                conexao = DriverManager.getConnection(url,usuario,senha);
            } catch (SQLException exp) {
                exp.printStackTrace();
                System.out.println("Falha ao estabalecer conexão!");

            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                System.out.println("Falha ao encontrar a classe " + "org.apache.derby.jdbc.ClientDriver");
            }
        }
        return conexao;
    }

    @Override
    public void close() {
        try {
            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Falha ao fechar a conexão!!");
        }
    }

}
