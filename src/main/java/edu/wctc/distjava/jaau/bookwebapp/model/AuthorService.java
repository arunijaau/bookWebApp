/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jaau.bookwebapp.model;

import edu.wctc.distjava.jaau.bookwebapp.repository.AuthorRepository;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    public Author findById(String id){
        return authorRepo.findOne(Integer.parseInt(id));
    }
    
    public void addAuthor(String authorName){
        Date dateAdded = new Date();
        Author author = new Author();
        author.setAuthorName(authorName);
        author.setDateAdded(dateAdded);
        
        authorRepo.save(author);
    }
    
    
    public void removeAuthorById(String id) throws ClassNotFoundException, IllegalArgumentException,
            SQLException, NumberFormatException {
        //No need to check for null because when we parse to an Integer it will be checked.
        if (id == null) {
            throw new IllegalArgumentException("id must be an Integer greater than 0.");
        }        
        
        Integer value = Integer.parseInt(id);
        String jpql = "delete from Author a where a.authorId = :id";
        Query q = getEm().createQuery(jpql);
        q.setParameter("id", value);
        q.executeUpdate();  
        
        //Here author is retrieved from cache. Therefore no need to merge first. Less efficient.
//        Author author = getEm().findById(Author.class,id);
//        getEm().remove(author);
        }
    
}
