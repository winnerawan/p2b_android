package id.ac.unipma.eapt.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import id.ac.unipma.eapt.EAPT;
import id.ac.unipma.eapt.data.DataManager;
import id.ac.unipma.eapt.di.module.ApplicationModule;
import id.ac.unipma.eapt.service.SyncService;
import dagger.Component;

import id.ac.unipma.eapt.di.ApplicationContext;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(EAPT app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();}
