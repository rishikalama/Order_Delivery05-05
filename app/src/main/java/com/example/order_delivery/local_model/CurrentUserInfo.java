package com.example.order_delivery.local_model;

import com.example.order_delivery.model.sz_customer;

//this needs to be updated
public class CurrentUserInfo {
    public static sz_customer currentUser;
    public static String currentUserId;
    public static String currentUserName;
    public static double currentUserBalance;
    public static boolean currentUserVip;
    public static int currentUserWarning;
    //get current row for user

    public CurrentUserInfo(sz_customer currentUser){
        this.currentUser = currentUser;
        this.currentUserId = currentUser.getObjectId();
        this.currentUserName = currentUser.getName();
        this.currentUserBalance = currentUser.getBalance();
        this.currentUserVip = currentUser.getVip();
        this.currentUserWarning = currentUser.getWarning();
    }
}
