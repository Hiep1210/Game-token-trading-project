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
        String oldPass = request.getParameter("oldpassword");
        String newPass = request.getParameter("password");
        String newCfPass = request.getParameter("cfpassword");
        User acc = (User) request.getSession().getAttribute("id");
        acc = (User) request.getSession().getAttribute("password");
        String opass = acc.getPassword();
        int user_id = acc.getId();
        if (!oldPass.equals(opass)){//check mat khau cu co dung khong
            request.setAttribute("mess1","Mật khẩu cũ không khớp. Vui lòng nhập lại!");
            request.getRequestDispatcher("./changePassword.jsp").forward(request, response);
        }else {
        if (newPass.equals(newCfPass)) {//check lai mat khau da nhap
            UserDAO ud = new UserDAO();
            ud.ChangePassword(user_id, newPass);
            response.sendRedirect("./changePasswordSuccess.jsp");
            
        }else {
            request.setAttribute("mess2", "Mật khẩu không khớp. Vui lòng nhập lại!");
            request.getRequestDispatcher("./changePassword.jsp").forward(request, response);
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
