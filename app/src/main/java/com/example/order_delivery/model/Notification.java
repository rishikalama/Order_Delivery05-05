package com.example.order_delivery.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Notification")
public class Notification extends ParseObject {
    public static final String KEY_USERNAME = "toUsername";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_SUBJECT = "subject";
    public static final String KEY_FROMUSER = "fromUser";

    public String getToUsername(){
        return getString(KEY_USERNAME);
    }

    public void setToUsername(String username){
        put(KEY_USERNAME, username);
    }

    public String getMessage(){
        return getString(KEY_MESSAGE);
    }
    public void setMessage(String message){
        put(KEY_MESSAGE, message);
    }

    public String getSubject(){
        return getString(KEY_SUBJECT);
    }

    public void setSubject(String subject){
        put(KEY_SUBJECT, subject);
    }

    public String getFromUser(){
        return getString(KEY_FROMUSER);
    }

    public void setFromUser(String fromUser){
        put(KEY_FROMUSER, fromUser);
    }

    //in terms of customer
    //Notification notification = new Notification()
    //notifcation.setToUsername(), setSubject, setMessage, setFrom
    //from is always current user
    //toUsername = manager
    //after setting everthing call
    //notification.saveInBackground()'

}
