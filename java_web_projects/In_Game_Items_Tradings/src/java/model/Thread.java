/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
/**
 *
 * @author ACER
 */
public class Thread {
   private int id;
   private String ttitle;
   private String tcontent;
   private String ttag;
   private String tauthor;
   private int user_id;
   
   public Thread(){
       
   }

    public void setId(int id) {
        this.id = id;
    }

    public void setTtitle(String ttitle) {
        this.ttitle = ttitle;
    }

    public void setTtag(String ttag) {
        this.ttag = ttag;
    }

    public void setTcontent(String tcontent) {
        this.tcontent = tcontent;
    }

    public void setTauthor(String tauthor) {
        this.tauthor = tauthor;
    }

    public String getTtitle() {
        return ttitle;
    }

    public String getTcontent() {
        return tcontent;
    }

    public String getTtag() {
        return ttag;
    }

    public String getTauthor() {
        return tauthor;
    }

    public int getId() {
        return id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Thread(int id, String ttitle, String tcontent, String ttag, String tauthor, int user_id) {
        this.id = id;
        this.ttitle = ttitle;
        this.tcontent = tcontent;
        this.ttag = ttag;
        this.tauthor = tauthor;
        this.user_id = user_id;
    }

  

}