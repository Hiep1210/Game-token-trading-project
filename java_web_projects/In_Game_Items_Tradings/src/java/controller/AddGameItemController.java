package controller;

import dao.GameItemsDAO;
import dao.PaymentRequestDAO;
import dao.RoleDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.GameItems;
import dao.SellDAO;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.ProtectionDomain;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PaymentRequest;
import model.Role;
import model.User;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "AddGameItemController", urlPatterns = {"/AddGameItemController"})
public class AddGameItemController extends HttpServlet {

    Logger logger
            = Logger.getLogger(SendPaymentRequestController.class.getName());
    String location = null;
    private static final String HOMEPAGE = "BuyPageController";
    
    
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        ServletFileUpload upload;
        FileItemIterator iterator;
        FileItemStream item;
        String picGameItem = null;
        GameItems gameItem;
        String newGameItemImage;
    try {
        User user = (User) request.getSession().getAttribute("user");

        String redirect = "addGameItem.jsp";
        if (user == null) {
            redirect = "BuyPageController";
        } else if (!isAdmin(user.getRoleid())) {
            redirect = "BuyPageController";
        } else {
             upload = new ServletFileUpload();
                iterator = upload.getItemIterator(request);
                item = iterator.next();
            String skinName = request.getParameter("skinName");
            String itemName = request.getParameter("itemName");
            String type = request.getParameter("type");
            String rarity = request.getParameter("rarity");
            picGameItem = item.getName();
            gameItem = new GameItems(skinName, itemName, type, rarity, picGameItem);
            newGameItemImage = GameItemsDAO.insertGameItem(gameItem);
            moveImages(item, newGameItemImage);
                    
                    
                     // Save the profile picture
        }
        request.getRequestDispatcher(redirect).forward(request, response);
    } catch (Exception e) {
        System.out.println(e);
    }
}

    public boolean isAdmin(int role_id) {
        ArrayList<Role> roleList = RoleDAO.getRoleList();
        boolean isAdmin = false;
        for (Role role : roleList) {
            if (role.getRole() == role_id && "admin".equals(role.getRole_name())) {
                isAdmin = true;
                break;
            }
        }
        return isAdmin;
    }
    
    private void moveImages(FileItemStream item, String newInvoiceImageName) {
        InputStream initialStream;
        Path targetDir;
        Path target;
        try {
            //Move image to new location in project folder called image
            if (location == null) {
                location = getLocation();
            }
            initialStream = item.openStream();
            targetDir = Paths.get(location);//get location of image file in project 
            target = targetDir.resolve(newInvoiceImageName + ".webp");//get location of copied image in the project(targetDir + id of inserted record + .webp)
            Files.copy(initialStream, target,
                    StandardCopyOption.REPLACE_EXISTING);//change name and copy image into target file
            IOUtils.closeQuietly(initialStream);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private String getLocation() {
        String path;
        String jarPath = null;
        ProtectionDomain domain;
        try {
            //get location of image file in project
            domain = SendPaymentRequestController.class.getProtectionDomain();
            path = domain.getCodeSource().getLocation().getPath();//get location of jar file in class News Controller
            jarPath = URLDecoder.decode(path, "UTF-8");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return jarPath.replace("build/web/WEB-INF/classes/", "").substring(1)
                + "web/UI/image/";//return the location invoice images file
    }

}
