package com.mta.trackerapplication.dagger;


import android.app.Application;
import android.content.Context;

import com.mta.trackerapplication.dagger.module.AppModule;
import com.mta.trackerapplication.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface ApplicationComponents {

    void inject(MainActivity mainActivity);

    Context getContext();

    Application getApplicationContext();
}
