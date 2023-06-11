/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This file describes the model of user 
 */
package model;

/**
 *
 * @author Inspiron
 */
public class User {

    private int id;
    private String username;
    private String password;
    private String dob;
    private String email;
    private String gender;
    private String avatar;
    private int role_id;
    private double money;

    public User() {
    }

    public User(int id, String username, String password, String dob, String email, String gender, String avatar, int role_id, double money) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.avatar = avatar;
        this.role_id = role_id;
        this.money = money;
    }

    public User(String avatar, String username, String email, String dob, String gender, String password) {
        this.avatar = avatar;
        this.username = username;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.password = password;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

}
