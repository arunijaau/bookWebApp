/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jaau.bookwebapp.controller;

import edu.wctc.distjava.jaau.bookwebapp.model.Author;
import edu.wctc.distjava.jaau.bookwebapp.model.AuthorService;



import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Aruni
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/authorController"})
public class AuthorController extends HttpServlet {

    
    
    public static final String ACTION = "action";
    public static final String LIST_ACTION = "list";
    public static final String DELETE_ACTION = "remove";
    public static final String ID = "id";
    public static final String EDIT_ACTION = "edit";
    public static final String ADD_ACTION = "add";
    public static final String LIST_PAGE = "/authorList.jsp";
    public static final String ADDEDIT_PAGE = "/addEditAuthor.jsp";
    public static final String GET_METHOD = "get";
    public static final String POST_METHOD = "post";

    
    private AuthorService authorService;


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String method)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String destination = LIST_PAGE; //default

        try {
            String action = request.getParameter(ACTION);

            List<Author> authorList = null;

            if (action.equalsIgnoreCase(DELETE_ACTION)) {
                String authorId = request.getParameter(ID);
                authorService.removeAuthorById(authorId);

            } else if (action.equalsIgnoreCase(EDIT_ACTION)) {
                String authorId = request.getParameter(ID);
                //changed
                Author author = authorService.findById(authorId);
                if (method.equals(GET_METHOD)) {
                    request.setAttribute("author", author);
                    request.setAttribute("action", EDIT_ACTION);
                    destination = ADDEDIT_PAGE;
                } else {
                    String name = request.getParameter("name");
                    String dateAdded = request.getParameter("dateAdded");
                    author.setAuthorName(name);
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    Date date = format.parse(dateAdded);
                    author.setDateAdded(date);
                    //changed
                    authorService.updateAuthor(author);
                    destination = LIST_PAGE;
                }

            } else if (action.equalsIgnoreCase(ADD_ACTION)) {
                if (method.equals(GET_METHOD)) {
                    destination = ADDEDIT_PAGE;
                    request.setAttribute("action", ADD_ACTION);
                } else {
                    destination = LIST_PAGE;
                    String name = request.getParameter("name");
                    String dateAdded = request.getParameter("dateAdded");
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    Date date = format.parse(dateAdded);
                    Author author = new Author();
                    author.setAuthorName(name);
                    author.setDateAdded(date);
                    //changed
                    authorService.addAuthor(author);
                }

            }
            if (destination.equals(LIST_PAGE)) {
                //changed
                authorList = authorService.findAll();
                request.setAttribute("authorList", authorList);
            }

        } catch (Exception e) {
            destination = LIST_PAGE;
            request.setAttribute("errMessage", e.getMessage());
        }

        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        //Ask Spring for object to inject
        ServletContext sctx = getServletContext();
        
        WebApplicationContext ctx
                = WebApplicationContextUtils.getWebApplicationContext(sctx);
        authorService = (AuthorService) ctx.getBean("authorService");
    }

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
        processRequest(request, response, GET_METHOD);
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
        processRequest(request, response, POST_METHOD);
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

}
