/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This files is controller for the feature log in
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import dao.UserDAO;

/**
 *
 * @author Inspiron
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        processRequest(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //if there is username given then find it
        if (username != null) {
            FindUserName(request, response ,username);
            //if there is password given then proceed to log in
            if (request.getParameter("password") != null) {
                LogIn(request, response,username,password);
            }
        }
    }

    //if there is username in db then send it back 
    private void FindUserName(HttpServletRequest request, HttpServletResponse response,String username) 
            throws IOException, ServletException {
        String name = UserDAO.FindUserName(username);
        if(name!=null){
            request.setAttribute("name", name);
        }//if not found, send a message
        else{
            request.setAttribute("message", "Username not found");
        }
        request.getRequestDispatcher("second.jsp").forward(request, response);
    }

    private void LogIn(HttpServletRequest request, HttpServletResponse response,String username,String password) 
            throws IOException, ServletException {
        User user = UserDAO.LogIn(username, password);
        if(user==null){
            request.setAttribute("message", "Password not found");
            request.getRequestDispatcher("second.jsp").forward(request, response);
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect("DisplayMarketItemsController");
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