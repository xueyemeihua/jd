package com.sky.pojo;

public class User {
    private Integer uid;
    private String username;
    private String pwd;
    private String uname;
    private String phone;
    private String cardid;
    private Integer money;

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", uname='" + uname + '\'' +
                ", phone='" + phone + '\'' +
                ", cardid='" + cardid + '\'' +
                ", money=" + money +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public User(Integer uid, String username, String pwd, String uname, String phone, String cardid, Integer money) {
        this.uid = uid;
        this.username = username;
        this.pwd = pwd;
        this.uname = uname;
        this.phone = phone;
        this.cardid = cardid;
        this.money = money;
    }

    public User() {
    }
}
