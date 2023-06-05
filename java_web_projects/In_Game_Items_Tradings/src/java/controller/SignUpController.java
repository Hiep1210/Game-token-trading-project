/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This files is controller for signing up 
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
import model.GameAccount;

/**
 *
 * @author Inspiron
 */
@WebServlet(name = "SignUpController", urlPatterns = {"/SignUpController"})
public class SignUpController extends HttpServlet {

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
            out.println("<title>Servlet SignUpController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpController at " + request.getContextPath() + "</h1>");
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
        SignUp(request, response);

    }

    private void SignUp(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(request.getParameter("game_account_id"));
        GameAccount game_acc = (GameAccount)request.getSession().getAttribute("game_acc");
        System.out.println(game_acc.getUsername());
        int game_id = game_acc.getId();
        if (username != null) {
            //if inputted username is found, then send a message to UI
            if (UserDAO.FindUserName(username) != null) {
                request.setAttribute("message", "Existed username, please re-input!");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
            //if there is password given then signup for user
            if (password != null) {
                User user = new User(0,username, password, game_id, 1, 0);
                boolean success = UserDAO.InsertUser(user);
                //if fail to add user then dend a message to UI
                if (!success) {
                    request.setAttribute("message", "Internal Error, failed to add user");
                    request.getRequestDispatcher("signup.jsp").forward(request, response);
                }
                request.getSession().setAttribute("user", user);
                response.sendRedirect("DisplayMarketItemsController");
            }
        }
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
