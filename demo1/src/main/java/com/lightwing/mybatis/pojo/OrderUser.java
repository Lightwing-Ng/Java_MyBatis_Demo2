package com.lightwing.mybatis.pojo;

/**
 * 订单关联用户的pojo
 *
 * @author Steven
 */
public class OrderUser extends Order {

    private String username;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order@User " +
                "\n\tName            | " + username +
                "\n\tAddress         | " + address +
                "\n\tgetId()         | " + getId() +
                "\n\tgetUserId()     | " + getUserId() +
                "\n\tgetNumber()     | " + getNumber() +
                "\n\tgetCreatetime() | " + getCreatetime() +
                "\n\tgetNote()       | " + getNote();
    }
}
