/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jaau.bookwebapp.controller;

import edu.wctc.distjava.jaau.bookwebapp.model.Author;
import edu.wctc.distjava.jaau.bookwebapp.model.AuthorService;
import edu.wctc.distjava.jaau.bookwebapp.model.Book;
import edu.wctc.distjava.jaau.bookwebapp.model.BookService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aruni
 */
@WebServlet(name = "BookController", urlPatterns = {"/bookController"})
public class BookController extends HttpServlet {
//
    public static final String ACTION = "action";
    public static final String LIST_ACTION = "list";
    public static final String DELETE_ACTION = "remove";
    public static final String ID = "id";
    public static final String EDIT_ACTION = "edit";
    public static final String ADD_ACTION = "add";
    public static final String LIST_PAGE = "/bookList.jsp";
    public static final String ADDEDIT_PAGE = "/addEditBook.jsp";
    public static final String BOOK = "book";
    public static final String BOOK_LIST = "bookList";
    public static final String ERR_MSG = "errMessage";
    public static final String BOOK_TITLE = "title";
    public static final String ISBN = "isbn";
    public static final String AUTHOR_ID = "author";
    public static final String AUTHORS = "authors";
    @EJB
    private BookService bookService;
    @EJB
    private AuthorService authorService;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");

        String destination;

        try {
            String action = request.getParameter(ACTION);

            switch (action.toLowerCase()) {
                case ADD_ACTION:
                    destination = doGetAdd(request);
                    break;
                case EDIT_ACTION:
                    destination = doGetEdit(request);
                    break;
                case LIST_ACTION:
                default:
                    destination = doGetList(request);
                    break;

            }

        } catch (Exception e) {
            destination = LIST_PAGE;
            request.setAttribute(ERR_MSG, e.getMessage());
        }

        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");

        String destination;

        try {
            String action = request.getParameter(ACTION);

            switch (action.toLowerCase()) {
                case DELETE_ACTION:
                    destination = doPostDelete(request);
                    break;
                case ADD_ACTION:
                    destination = doPostAdd(request);
                    break;
                case EDIT_ACTION:
                    destination = doPostEdit(request);
                    break;
                default:
                    destination = doGetList(request);
                    break;
            }

        } catch (Exception e) {
            destination = LIST_PAGE;
            request.setAttribute(ERR_MSG, e.getMessage());
        }

        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String doGetAdd(HttpServletRequest request) {
        request.setAttribute(ACTION, ADD_ACTION);
        List<Author> authors = authorService.findAll();
        request.setAttribute(AUTHORS, authors);
        return ADDEDIT_PAGE;
    }

    private String doGetEdit(HttpServletRequest request) {
        String bookId = request.getParameter(ID);
        Book book = bookService.findById(Integer.parseInt(bookId));
        List<Author> authors = authorService.findAll();
        book.getAuthorId();
        request.setAttribute(ACTION, EDIT_ACTION);
        request.setAttribute(BOOK, book);
        request.setAttribute(AUTHORS, authors);
        return ADDEDIT_PAGE;
    }

    private String doGetList(HttpServletRequest request) {
        List<Book> bookList = bookService.findAll();
        request.setAttribute(BOOK_LIST, bookList);
        return LIST_PAGE;
    }

    private String doPostDelete(HttpServletRequest request) throws ClassNotFoundException, IllegalArgumentException, SQLException {
        String bookId = request.getParameter(ID);
        bookService.remove(bookService.findById(new Integer(bookId)));
        return doGetList(request);
    }

    private String doPostAdd(HttpServletRequest request) {
        String title = request.getParameter(BOOK_TITLE);
        String isbn = request.getParameter(ISBN);
        String authorId = request.getParameter(AUTHOR_ID);
        Author newAuthor = authorService.findById(Integer.parseInt(authorId));
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthorId(newAuthor);
        bookService.create(book);
        return doGetList(request);
    }

    private String doPostEdit(HttpServletRequest request) {
        String bookId = request.getParameter(ID);
        Book book = bookService.findById(Integer.parseInt(bookId));
        String title = request.getParameter(BOOK_TITLE);
        String isbn = request.getParameter(ISBN);
        String authorId = request.getParameter(AUTHOR_ID);
        Author newAuthor = authorService.findById(Integer.parseInt(authorId));
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthorId(newAuthor);
        bookService.edit(book);
        return doGetList(request);
    }

}
