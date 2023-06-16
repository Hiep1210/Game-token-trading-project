/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This files is controller for signing up 
 */
package controller;

import dao.PaymentRequestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import dao.UserDAO;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.ProtectionDomain;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.PaymentRequest;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

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
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void doPost1(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        SignUp(request, response);
//    }
//
//    private void SignUp(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String avatar = request.getParameter("avatar");
//        String email = request.getParameter("email");
//        String dob = request.getParameter("dob");
//        String gender = request.getParameter("gender");
//        String password = request.getParameter("password");
//        if (username != null) {
//            //if inputted username is found, then send a message to UI
//            if (UserDAO.FindUserName(username) != null) {
//                request.setAttribute("message", "Existed username, please re-input!");
//                request.getRequestDispatcher("signup.jsp").forward(request, response);
//            }
//            //if there is password given then signup for user
//            if (password != null) {
//                User user = new User(0, username, password, dob, email, gender, avatar, 1, 0);
//                boolean success = UserDAO.InsertUser(user);
//                //if fail to add user then dend a message to UI
//                if (!success) {
//                    request.setAttribute("message", "Internal Error, failed to add user");
//                    request.getRequestDispatcher("signup.jsp").forward(request, response);
//                }
//                request.getSession().setAttribute("user", user);
//                response.sendRedirect("BuyPageController");
//            }
//        }
//    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletFileUpload upload;
        FileItemIterator iterator;
        FileItemStream item;
        String username = null;
        String password = null;
        String email = null;
        String dob = null;
        String gender = null;
        String profilePicFilename = null;

        try {
            upload = new ServletFileUpload();
            iterator = upload.getItemIterator(request);
            int index = 0;
            while (iterator.hasNext()) {
                item = iterator.next();
                if (item.isFormField()) { // Process form fields
                    String fieldName = item.getFieldName();
                    String fieldValue = Streams.asString(item.openStream());
                    switch (fieldName) {
                        case "username":
                            username = fieldValue;
                            break;
                        case "password":
                            password = fieldValue;
                            break;
                        case "email":
                            email = fieldValue;
                            break;
                        case "dob":
                            dob = fieldValue;
                            break;
                        case "gender":
                            gender = fieldValue;
                            break;
                        default:
                            break;
                    }
                } else { // Process file upload
                    profilePicFilename = item.getName(); // Get the original filename of the uploaded profile picture
                    saveProfilePicture(item); // Save the profile picture
                }
                index++;
            }
            
            //If inputted username is found in db, then send a message to UI
            if (UserDAO.FindUserName(username) != null) {
                request.setAttribute("message", "Existed username, please re-input!");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
            // Create a User object and perform sign-up logic
            User newUser = new User(0, username, password, dob, email, gender, profilePicFilename, 1, 0);
            // Perform sign-up logic using the newUser object
            UserDAO dao = new UserDAO();
            dao.insertUser(newUser);
            // Redirect the user to the appropriate page after sign-up
            request.getSession().setAttribute("user", newUser);
            response.sendRedirect("BuyPageController");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    String location = null;

    private void saveProfilePicture(FileItemStream item) {
        InputStream initialStream;
        Path targetDir;
        Path target;
        try {
            if (location == null) {
                location = getLocation();
            }
            initialStream = item.openStream();
            targetDir = Paths.get(location, "profile_pics"); // Create a "profile_pics" directory inside the location
            targetDir.toFile().mkdirs(); // Create the directory if it doesn't exist
            target = targetDir.resolve(item.getName()); // Save the profile picture with its original filename
            Files.copy(initialStream, target, StandardCopyOption.REPLACE_EXISTING);
            IOUtils.closeQuietly(initialStream);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String getLocation() {
        String path;
        String jarPath = null;
        ProtectionDomain domain;
        try {
            domain = SendPaymentRequestController.class.getProtectionDomain();
            path = domain.getCodeSource().getLocation().getPath();
            jarPath = URLDecoder.decode(path, "UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }
        return jarPath.replace("build/web/WEB-INF/classes/", "").substring(1)
                + "web/UI/image/"; // Update this path to the desired location for profile pictures
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
