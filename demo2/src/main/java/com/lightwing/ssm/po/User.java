package com.lightwing.ssm.po;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private Date birthday;

    private String sex;

    private String address;

    private String uuid2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getUuid2() {
        return uuid2;
    }

    public void setUuid2(String uuid2) {
        this.uuid2 = uuid2 == null ? null : uuid2.trim();
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "\n\tName      " + username +
                "\n\tGender    " + sex +
                "\n\tBirthday  " + birthday.getYear() + "/" + (birthday.getMonth() + 1) +
                "\n\tAddress   " + address +
                "\n\tuuid2     " + uuid2;
    }
}