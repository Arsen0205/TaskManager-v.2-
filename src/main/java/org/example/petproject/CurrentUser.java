package org.example.petproject;

public class CurrentUser {

    private static int userId;

    public static void setUserId(int id){
        userId = id;
    }

    public static int getUserId(){
        return userId;
    }
}
