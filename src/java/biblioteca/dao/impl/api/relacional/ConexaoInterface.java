/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao.impl.api.relacional;

import java.sql.Connection;

/**
 *
 * @author 41445368
 */
public interface ConexaoInterface {

    Connection getConnection();

    void close();
}
