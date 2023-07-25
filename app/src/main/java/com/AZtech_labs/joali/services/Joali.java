package com.AZtech_labs.joali.services;

import android.app.Application;
import android.graphics.Typeface;
import android.provider.SyncStateContract;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatDelegate;

import com.AZtech_labs.joali.MainActivity;
import com.AZtech_labs.joali.R;

import java.io.File;
import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Joali extends Application {
    private static Joali mInstance;
    private  TypeFactory typeFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        TypeFactory.setDefaultFont(this, "DEFAULT", "fonts/Roboto-Regular.ttf");
        TypeFactory.setDefaultFont(this, "MONOSPACE", "fonts/Roboto-Thin.ttf");
        TypeFactory.setDefaultFont(this, "SERIF", "fonts/Roboto-Light.ttf");
        TypeFactory.setDefaultFont(this, "SANS_SERIF", "fonts/Roboto-Medium.ttf");

        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);



        //BackgroundHome.run(get);
    }




}
