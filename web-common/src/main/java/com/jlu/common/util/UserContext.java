package com.jlu.common.util;

public class UserContext {

    private static final ThreadLocal<Long>  tl = new ThreadLocal<Long>();
    //保存id
    public static void setUserId(Long userId){

        tl.set(userId);

    }
    public static Long getUserInfo(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
