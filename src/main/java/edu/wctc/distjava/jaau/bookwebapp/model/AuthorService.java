/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jaau.bookwebapp.model;

import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Aruni
 */
@Stateless
public class AuthorService extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEm() {
        return em;
    }

    public AuthorService() {
        super(Author.class);
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
