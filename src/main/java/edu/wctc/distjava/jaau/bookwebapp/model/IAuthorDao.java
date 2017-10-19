/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jaau.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aruni
 */
public interface IAuthorDao {
    public abstract Author getAuthorById(String colValue) throws ClassNotFoundException, SQLException, 
            IllegalArgumentException;
    
    public abstract int addAuthor(List<String> colNames, List<Object> colValues) throws ClassNotFoundException, SQLException, 
            IllegalArgumentException;
    
    public abstract int updateAuthor(List<String> colNames, List<Object> colValues, Object pkValue) throws ClassNotFoundException, SQLException;
    
    public abstract int removeAuthorById(Integer id) throws ClassNotFoundException, IllegalArgumentException, SQLException;
    
    public abstract List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException;
    
}
