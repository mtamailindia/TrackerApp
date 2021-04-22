package com.mta.trackerapplication.dagger.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    public Application getApplicationContext(){
        return application;
    }

    @Provides
    public Context getContext(){
        return application.getApplicationContext();
    }
}
