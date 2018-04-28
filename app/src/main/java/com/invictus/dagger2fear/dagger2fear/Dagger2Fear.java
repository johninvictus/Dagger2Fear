package com.invictus.dagger2fear.dagger2fear;

import com.invictus.dagger2fear.dagger2fear.dagger.component.AppComponent;
import com.invictus.dagger2fear.dagger2fear.dagger.component.DaggerAppComponent;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

/**
 * Created by invictus on 4/28/18.
 */

public class Dagger2Fear extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();

        appComponent.inject(this);
        return appComponent;
    }
}