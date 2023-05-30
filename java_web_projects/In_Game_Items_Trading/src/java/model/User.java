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
    private int game_id;
    private double money;

    public User(int id, String username, String password, int game_id, double money) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.game_id = game_id;
        this.money = money;
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

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    
}
