package id.ac.unipma.eapt.di.module;

import android.app.Application;
import android.content.Context;



import javax.inject.Singleton;

import id.ac.unipma.eapt.data.db.DbHelper;
import id.ac.unipma.eapt.utils.AppConstants;
import dagger.Module;
import dagger.Provides;
import id.ac.unipma.eapt.data.AppDataManager;
import id.ac.unipma.eapt.data.DataManager;
import id.ac.unipma.eapt.data.db.AppDbHelper;
import id.ac.unipma.eapt.data.network.ApiHelper;
import id.ac.unipma.eapt.data.network.AppApiHelper;
import id.ac.unipma.eapt.data.prefs.AppPreferencesHelper;
import id.ac.unipma.eapt.data.prefs.PreferencesHelper;
import id.ac.unipma.eapt.di.ApplicationContext;
import id.ac.unipma.eapt.di.DatabaseInfo;
import id.ac.unipma.eapt.di.PreferenceInfo;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

//    @Provides
//    @Singleton
//    ApiHeader provideApiHeader(DbHelper header) {
//        return header;
//    }



}
