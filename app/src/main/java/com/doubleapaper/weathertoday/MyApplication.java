package com.doubleapaper.weathertoday;

import android.app.Application;

import com.doubleapaper.weathertoday.Object.Header;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .schemaVersion(4)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.getInstance(config);
        Realm.setDefaultConfiguration(config);

    }
}
