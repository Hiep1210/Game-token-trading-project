/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This files is controller for changing linked game account
*/

package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.GameAccount;
/**
 *
 * @author Inspiron
 */
@WebServlet(name="ChangeLinkedGameAccount", urlPatterns={"/ChangeLinkedGameAccount"})
public class ChangeLinkedGameAccount extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangeLinkedGameAccount</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeLinkedGameAccount at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        changeLinkedGameAccount(request, response);
    } 
    
    private void changeLinkedGameAccount(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
       int user_id = user.getId();
       GameAccount game_acc = (GameAccount) request.getSession().getAttribute("game_acc");
       int linked_acc = game_acc.getId();
        System.out.println(linked_acc);
       String message ;//m
       message = "failed update";
       //if update successfully, change message sent to UI
       if(UserDAO.updateLinkedGameAccount(user_id, linked_acc)){
           message= "success update";
       }
       request.setAttribute("message", message);
       request.getRequestDispatcher("UserProfileController").forward(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
