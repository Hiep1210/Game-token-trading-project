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
import org.apache.tomcat.util.http.fileupload.util.Streams;

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
        String skinName = null;
        String itemName = null;
        String type = null;
        String rarity = null;
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
                while (iterator.hasNext()) {
                    item = iterator.next();
                    if (item.isFormField()) { // Process form fields
                        String fieldName = item.getFieldName();
                        String fieldValue = Streams.asString(item.openStream());
                        switch (fieldName) {
                            case "skinName":
                                skinName = fieldValue;
                                break;
                            case "itemName":
                                itemName = fieldValue;
                                break;
                            case "type":
                                type = fieldValue;
                                break;
                            case "rarity":
                                rarity = fieldValue;
                                break;
                            default:
                                break;
                        }
                    } else { // Process file upload
                        // Get the original filename of the uploaded profile picture
                        picGameItem = item.getName().substring(0,item.getName().lastIndexOf("."));
                        //User not select any picture
                        if ("".equals(picGameItem)) {
                            redirect = "BuyPageController";
                            break;
                        }
                        saveGameItemPic(item); // Save the profile picture
                    }
                }
                gameItem = new GameItems(skinName, itemName, type, rarity, picGameItem);
                GameItemsDAO.insertGameItem(gameItem);
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

    private void saveGameItemPic(FileItemStream item) {
        InputStream initialStream;
        Path targetDir;
        Path target;
        try {
            if (location == null) {
                location = getLocation();
            }
            initialStream = item.openStream();
            targetDir = Paths.get(location, "image"); // Create a "profile_pics" directory inside the location
            targetDir.toFile().mkdirs(); // Create the directory if it doesn't exist
            target = targetDir.resolve(item.getName().substring(0,item.getName().lastIndexOf("."))); // Save the profile picture with its original filename
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
            domain = EditUserProfile.class.getProtectionDomain();
            path = domain.getCodeSource().getLocation().getPath();
            jarPath = URLDecoder.decode(path, "UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }
        return jarPath.replace("build/web/WEB-INF/classes/", "").substring(1)
                + "web/UI/"; // Update this path to the desired location for profile pictures
    }

}
