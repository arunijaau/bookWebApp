/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jaau.bookwebapp.model;

import edu.wctc.distjava.jaau.bookwebapp.repository.AuthorRepository;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aruni
 */
@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepo;
    
    public AuthorService() {
        
    }
    
    //how to use spring repository to do basic crud operation
    public List<Author> findAll(){
        return authorRepo.findAll();
    }
    
    public Author findById(String id) throws DataAccessException {
        return authorRepo.findOne(Integer.parseInt(id));
    }
    
    public Author findById(int id){
        return authorRepo.findOne(id);
    }
    
    public void updateAuthor(String id, String authorName){
        Author author = findById(id);
        author.setAuthorName(authorName);
        authorRepo.save(author);
    }
    
    public void updateAuthor(Author author){
        authorRepo.save(author);
    }
    
    public void addAuthor(String authorName){
        Date dateAdded = new Date();
        Author author = new Author();
        author.setAuthorName(authorName);
        author.setDateAdded(dateAdded);
        author.setBookSet(new HashSet());
        
        authorRepo.save(author);
    }
    
    public void addAuthor(Author author){
        authorRepo.save(author);
    }
    
    public void removeAuthorById(String id) throws ClassNotFoundException, IllegalArgumentException,
            SQLException, NumberFormatException {
        
        Integer value = Integer.parseInt(id);      
        authorRepo.delete(value);
  

        }
    
    
}
