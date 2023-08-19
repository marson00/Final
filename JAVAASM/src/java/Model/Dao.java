/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao <T>{
    
    Optional<T> find (String accountID) throws SQLException;
    List <T> findAll() throws SQLException;
    boolean save (T i) throws SQLException;
    boolean update (T i) throws SQLException;
}
