package com.pie.planingjakartaee.dao;

public final class DaoFactory {

    public static UserDao getUserDao() {
        return new UserDao();
    }


}
