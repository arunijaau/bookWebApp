/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jaau.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;



/**
 *
 * @author Aruni
 */
public class AuthorService {
    private IAuthorDao authorDao;
    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK = "author_id";
    
    public AuthorService(IAuthorDao authorDao){
        setAuthorDao(authorDao);
    }
    
    public final int addAuthor(List<String> colNames, List<Object> colValues ) throws ClassNotFoundException, SQLException{
        return authorDao.addAuthor(colNames,colValues);
    }
    
    public final int addAuthor(Author author) throws ClassNotFoundException, SQLException{
        List<String> colNames = new ArrayList<>();        
        colNames.add("author_name");
        colNames.add("date_added");
        
        List<Object> colValues = new ArrayList<>();        
        colValues.add(author.getAuthorName());
        colValues.add(author.getDateAdded());
        
        return addAuthor(colNames, colValues);
    }
    
    public final int updateAuthor(Author author)  throws 
            ClassNotFoundException, SQLException{
        
        List<String> colNames = new ArrayList<>();
        colNames.add("author_name");
        colNames.add("date_added");
        
        List<Object> colValues = new ArrayList<>();
        colValues.add(author.getAuthorName());
        colValues.add(author.getDateAdded());
        
        return updateAuthor(colNames, colValues, author.getAuthorId().toString());
    }
    
    public final int updateAuthor(List<String> colNames, List<Object> colValues, Object pkValue) throws 
            ClassNotFoundException, SQLException{
        return authorDao.updateAuthor(colNames, colValues, pkValue);
    }
    
    public final int removeAuthorById(String id) throws ClassNotFoundException, IllegalArgumentException, 
            SQLException, NumberFormatException{
        if(id == null){
            throw new IllegalArgumentException("id must be an Integer greater than 0.");
            
        }
        Integer value = Integer.parseInt(id);
        return authorDao.removeAuthorById(value);
        
    }
    
    public Author findAuthor(String pkValue) throws ClassNotFoundException, SQLException{
        if(pkValue == null || pkValue.isEmpty()){
            throw new IllegalArgumentException("Column value must be provided.");
        }
        return authorDao.getAuthorById(pkValue);
    }
            
    public List<Author> getAuthorList() 
            throws SQLException, ClassNotFoundException{
        return authorDao.getListOfAuthors();        
    }

    public IAuthorDao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(IAuthorDao authorDao) {
        this.authorDao = authorDao;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        IAuthorDao dao = new AuthorDao(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root", "admin",
                new MySqlDataAccess()
        );
        
        AuthorService authorService = new AuthorService(dao);
        
        int recsDeleted = authorService.removeAuthorById("2");
        
        List<Author> list = authorService.getAuthorList();
        
        for(Author a: list){
            System.out.println(a.getAuthorId() + ", "
            + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
        }
    }
    
}
