/*
*Programmer: Ly The Luong 
*Description: This files is controller for Change Password
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
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author ACER
 */
@WebServlet(name="ChangePasswordController", urlPatterns={"/ChangePasswordController"})
public class ChangePasswordController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
        String oldPass = request.getParameter("oldpass");
        String newPass = request.getParameter("newpass");
        String newCfPass = request.getParameter("cfpass");
        UserDAO dao = new UserDAO();
        HttpSession ses = request.getSession();
        User user = (User) request.getSession().getAttribute("user");
        if(!oldPass.equals(user.getPassword())){
            ses.setAttribute("mess1", "Old password not match");
            request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
        }else{
            if(newPass.equals(newCfPass)){
                dao.ChangePassword(user.getId(), newPass);
                ses.setAttribute("mess3", "Suceed");
                request.getRequestDispatcher("userProfile_1.jsp").forward(request, response);
            }else{
                ses.setAttribute("mess2", "New pass and confirm not match");
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            }
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
        processRequest(request, response);
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
