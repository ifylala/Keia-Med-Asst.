package com.clevmania.keia.ui;

import android.content.Context;
import android.content.SharedPreferences;

public class IntroManager {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    public IntroManager(Context context){
        this.context = context;
        preferences = context.getSharedPreferences("FirstInstallation",Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setAppsFirstLaunch(boolean isFirstRun){
        editor.putBoolean("AppsFirstRun", isFirstRun);
        editor.commit();
    }

    public boolean IsFirstLaunch(){
        return  preferences.getBoolean("AppsFirstRun",true);
    }
}
