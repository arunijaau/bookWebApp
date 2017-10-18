/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jaau.bookwebapp.controller;

import edu.wctc.distjava.jaau.bookwebapp.model.Author;
import edu.wctc.distjava.jaau.bookwebapp.model.AuthorDao;
import edu.wctc.distjava.jaau.bookwebapp.model.AuthorService;
import edu.wctc.distjava.jaau.bookwebapp.model.IAuthorDao;

import edu.wctc.distjava.jaau.bookwebapp.model.MySqlDataAccess;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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

            IAuthorDao dao = new AuthorDao(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/book",
                    "root", "admin",
                    new MySqlDataAccess()
            );

            AuthorService authorService = new AuthorService(dao);

            List<Author> authorList = null;

            if (action.equalsIgnoreCase(DELETE_ACTION)) {
                String authorId = request.getParameter(ID);
                authorService.removeAuthorById(authorId);
            } else if (action.equalsIgnoreCase(EDIT_ACTION)) {
                String authorId = request.getParameter(ID);
                Author author = authorService.findAuthor(authorId);

                if (method == GET_METHOD) {
                    request.setAttribute("author", author);
                    destination = ADDEDIT_PAGE;
                } else {
                    String name = request.getParameter("name");
                    String dateAdded = request.getParameter("dateAdded");
                    author.setAuthorName(name);
                    //author.setDateAdded(dateAdded);
                    authorService.updateAuthor(author);
                    destination = LIST_PAGE;
                }

            } else if (action.equalsIgnoreCase(ADD_ACTION)) {
                if (method == GET_METHOD) {
                    destination = ADDEDIT_PAGE;
                } else {
                    destination = LIST_PAGE;
                }
                String name = request.getParameter("name");
                String dateAdded = request.getParameter("dateAdded");
            }
            if (destination == LIST_PAGE) {
                authorList = authorService.getAuthorList();
                request.setAttribute("authorList", authorList);
            }

        } catch (Exception e) {
            destination = LIST_PAGE;
            request.setAttribute("errMessage", e.getMessage());
        }

        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
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
