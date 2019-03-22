package com.example.gu_rl.com.session;

import android.content.Context;
import android.content.SharedPreferences;

public class CurrentUser {

       private SharedPreferences sharedPreferences;
       private Context context;
       //private String name;

    public CurrentUser(Context context) {

        this.context = context;
        sharedPreferences = context.getSharedPreferences("current_user_login_preferences",Context.MODE_PRIVATE);
    }
    //{}
    public void writeLoginStatus(boolean status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Current_user_login_status", status);
        editor.commit();
    }

    public boolean readLoginStatus(){
        boolean status = false;
        status =sharedPreferences.getBoolean("Current_user_login_status", false);
        return status;
    }

}
