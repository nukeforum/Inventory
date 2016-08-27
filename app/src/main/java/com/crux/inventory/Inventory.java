package com.crux.inventory;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Inventory extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(getApplicationContext())
                .schemaVersion(1)
                .build()
        );
    }
}
