package com.lightwing.mybatis.pojo;

import java.util.List;

/**
 * 包装的 pojo
 *
 * @author Lightwing Ng
 */
public class QueryVo {
    private User user;

    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
