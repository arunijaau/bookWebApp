/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jaau.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class AuthorServiceOld implements Serializable{

    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK = "author_id";

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    public AuthorServiceOld() {
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    
//    public int addAuthor(List<String> colNames, List<Object> colValues) throws ClassNotFoundException, SQLException {
////        return authorDao.addAuthor(colNames,colValues);
//        return 0;
//    }

    public void addAuthor(Author author) throws ClassNotFoundException, SQLException {
        getEm().persist(author);
        
    }

    public void updateAuthor(Author author) throws
            ClassNotFoundException, SQLException {
        getEm().merge(author);
        
    }

//    public void updateAuthor(String name,Date dateAdded, Object pkValue) throws
//            ClassNotFoundException, SQLException {
////        
//        Integer value = (Integer)pkValue;
//        String jpql = "update Author a set a.authorName = :name "
//                + "and a.dateAdded = :date where a.authorId = :id";
//        Query q = getEm().createQuery(jpql);
//        q.setParameter("id",value);
//        q.setParameter("name", name);
//        q.setParameter("date", dateAdded);
//        q.executeUpdate();
    
    //Instructor solution
//    Author author = getEm().find(Author.class,new Integer(id))
//    author.setAuthorName(name);
//    getEm().merge(author);
    
//    }

    //It is optional to return an int value only if you want.
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
//        Author author = getEm().find(Author.class,id);
//        getEm().remove(author);
        }
    
//    public void removeAuthor(Author author){
//        //first have to merge before deleting and then delete
//        getEm().remove(getEm().merge(author));
//    }

    public Author findAuthor(String pkValue) throws ClassNotFoundException, SQLException {
        if (pkValue == null || pkValue.isEmpty()) {
            throw new IllegalArgumentException("Column value must be provided.");
        }
        Integer pkVal = Integer.parseInt(pkValue);
        String jpql = "Select a from Author a where a.authorId = :id";
        TypedQuery<Author> q = getEm().createQuery(jpql,Author.class);
        q.setParameter("id",pkVal);
        return q.getSingleResult();
    }

    public List<Author> getAuthorList() throws Exception{
        String jpql = "Select a from Author a";
        TypedQuery<Author> q = getEm().createQuery(jpql,Author.class);
        q.setMaxResults(500);  //optional     
        return q.getResultList();
    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        IAuthorDao dao = new AuthorDao(
//                "com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/book",
//                "root", "admin",
//                new MySqlDataAccess()
//        );
//
//        AuthorServiceOld authorService = new AuthorServiceOld(dao);
//
//        int recsDeleted = authorService.removeAuthorById("2");
//
//        List<Author> list = authorService.getAuthorList();
//
//        for (Author a : list) {
//            System.out.println(a.getAuthorId() + ", "
//                    + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
//        }
//    }
}
