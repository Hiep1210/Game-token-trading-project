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
   private String ticon;
   private String ttag;
   
   public Thread(){
       
   }

    public Thread(int id, String ttitle, String tcontent, String ticon, String ttag) {
        this.id = id;
        this.ttitle = ttitle;
        this.tcontent = tcontent;
        this.ticon = ticon;
        this.ttag = ttag;
    }

    public int getId() {
        return id;
    }

    public String getTcontent() {
        return tcontent;
    }

    public String getTicon() {
        return ticon;
    }

    public String getTtag() {
        return ttag;
    }

    public String getTtitle() {
        return ttitle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTcontent(String tcontent) {
        this.tcontent = tcontent;
    }

    public void setTicon(String ticon) {
        this.ticon = ticon;
    }

    public void setTtag(String ttag) {
        this.ttag = ttag;
    }

    public void setTtitle(String ttitle) {
        this.ttitle = ttitle;
    }
   
}

