/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao.api;

import java.sql.SQLException;


public interface DaoGenerico<E>{

    // CRUD

    public void delete(E Objeto) throws SQLException;

    public void create(E objeto) throws SQLException;

    public E read(E objeto) throws SQLException;

    public void update(E objeto) throws SQLException;

}
