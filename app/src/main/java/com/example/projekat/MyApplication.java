package com.example.projekat;

import android.app.Application;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.example.projekat.Models.Message;

import java.util.List;

public class MyApplication extends Application {

    public static final String APPLICATION_ID = "2CEA8AA3-482C-1566-FFF4-764058C06D00";
    public static final String API_KEY = "0718553B-5E37-5652-FF41-1A57C6191C00";
    public static final String SERVER_URL = "https://api.backendless.com";

    public static BackendlessUser auth;
    public static List<Message> messages;

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl(SERVER_URL);
        Backendless.initApp( getApplicationContext(), APPLICATION_ID, API_KEY);

    }
}
