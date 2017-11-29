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
public class BookService extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEm() {
        return em;
    }

    public BookService() {
        super(Book.class);
    }
    
    public void removeBookById(int id) throws ClassNotFoundException, IllegalArgumentException,
            SQLException, NumberFormatException {
        
        //Integer value = Integer.parseInt(id);
        String jpql = "delete b from Book b where b.bookId = :id";
        Query q = getEm().createQuery(jpql);
        q.setParameter("id", id);
        q.executeUpdate();  
    
//    public void addOrUpdateBook(String bookId, String title, String isbn, String authorId){
//         Book book = null;
//        
//        if(bookId == null || bookId.isEmpty()){
//            //must be new record
//            book = new Book();
//                
//        }else{
//            //must be updated record
//             book = new Book(new Integer(bookId));
//        }
//            book.setTitle(title);
//            book.setIsbn(isbn);
//            Author author = getEm().find(Author.class,new Integer(authorId));
//            book.setAuthorId(author);
//            
//            getEm().merge(book); 
//       
    }
}
