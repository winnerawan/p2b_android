package id.ac.unipma.eapt.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.ac.unipma.eapt.data.db.DbHelper;
import id.ac.unipma.eapt.data.network.ApiHelper;
import id.ac.unipma.eapt.data.network.model.LoginResponse;
import id.ac.unipma.eapt.data.network.model.Resp;
import id.ac.unipma.eapt.data.prefs.PreferencesHelper;
import id.ac.unipma.eapt.di.ApplicationContext;
import io.reactivex.Single;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

//    @Override
//    public ApiHeader getApiHeader() {
//        return mApiHelper.getApiHeader();
//    }


    @Override
    public boolean isLoggedIn() {
        return mPreferencesHelper.isLoggedIn();
    }

    @Override
    public void setLoggedIn(boolean loggedIn) {
        mPreferencesHelper.setLoggedIn(loggedIn);
    }


    @Override
    public boolean isFirstTime() {
        return mPreferencesHelper.isFirstTime();
    }

    @Override
    public void setFirstTime(boolean isFirstTime) {
        mPreferencesHelper.setFirstTime(isFirstTime);
    }

    @Override
    public Single<LoginResponse> login(String email, String password) {
        return mApiHelper.login(email, password);
    }

    @Override
    public Single<Resp> register(String email, String password, int type) {
        return mApiHelper.register(email, password, type);
    }

    @Override
    public void setIsStudent(int isStudent) {
        mPreferencesHelper.setIsStudent(isStudent);
    }

    @Override
    public int isStudent() {
        return mPreferencesHelper.isStudent();
    }
}

