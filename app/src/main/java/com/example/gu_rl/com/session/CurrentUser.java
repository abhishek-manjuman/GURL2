package com.example.gu_rl.com.session;

import android.content.Context;
import android.content.SharedPreferences;

public class CurrentUser {

       private SharedPreferences sharedPreferences;
       private Context context;
       //public String userId;
       //private String name;

    public CurrentUser(Context context) {

        this.context = context;
        sharedPreferences = context.getSharedPreferences("current_user_login_preferences",Context.MODE_PRIVATE);
    }
    //{}
    public void writeLoginStatus(boolean status, String userId){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Current_user_login_status", status);
        editor.putString("Current_user_id", userId);
        editor.commit();
    }

    public boolean readLoginStatus(){
        boolean status = false;
        String userid = "";
        status =sharedPreferences.getBoolean("Current_user_login_status", false);
        return status;
    }

    public String getCurrentUserId(){
        String userId = "";
        userId =sharedPreferences.getString("Current_user_id", "");
        return userId;
    }

}
