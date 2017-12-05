/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jaau.bookwebapp.model;

import edu.wctc.distjava.jaau.bookwebapp.repository.AuthorRepository;
import edu.wctc.distjava.jaau.bookwebapp.repository.BookRepository;
import java.sql.SQLException;
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
public class BookService {
    
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private AuthorRepository authorRepo;
    
    public BookService() {
        
    }
    
    public List<Book> findAll(){
        return bookRepo.findAll();
    }
    
    public Book findById(int id){
        return bookRepo.findOne(id);
    }
    
    public void updateBook(Book book){
        bookRepo.save(book);
    }
    
    public void addNewBook(String title, String isbn, String authorId){
    //Author should be there for a book to create(Relational Integrity)        
        Author author = authorRepo.findOne(Integer.parseInt(authorId));
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthorId(author);
        bookRepo.save(book);
    }
    
    public void addNewBook(Book book){
        bookRepo.save(book);
    }
    
    public void removeBookById(int id) {        
        bookRepo.delete(id);
        //Integer value = Integer.parseInt(id);
        
//        String jpql = "delete b from Book b where b.bookId = :id";
//        Query q = getEm().createQuery(jpql);
//        q.setParameter("id", id);
//        q.executeUpdate();  
       
    }
}
