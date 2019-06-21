package id.ac.unipma.eapt.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.ac.unipma.eapt.di.ApplicationContext;
import id.ac.unipma.eapt.di.PreferenceInfo;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String KEY_LOGGED_IN = "KEY_LOGGED_IN";
    private static final String KEY_FIRST_TIME = "KEY_FIRST_TIME";
    private static final String KEY_IS_STUDENT = "KEY_IS_STUDENT";
    private static final String KEY_NAME = "KEY_NAME";
    private static final String KEY_PARTICIPANT_ID = "KEY_PARTICIPANT_ID";
    private static final String KEY_STEP1 = "KEY_STEP_ONE";
    private static final String KEY_STEP2 = "KEY_STEP_TWO";


    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public boolean isLoggedIn() {
        return mPrefs.getBoolean(KEY_LOGGED_IN, false);
    }

    @Override
    public void setLoggedIn(boolean loggedIn) {
        mPrefs.edit().putBoolean(KEY_LOGGED_IN, loggedIn).apply();
    }

    @Override
    public boolean isFirstTime() {
        return mPrefs.getBoolean(KEY_FIRST_TIME, true);
    }

    @Override
    public void setFirstTime(boolean isFirstTime) {
        mPrefs.edit().putBoolean(KEY_FIRST_TIME, isFirstTime).apply();
    }

    @Override
    public void setIsStudent(int isStudent) {
        mPrefs.edit().putInt(KEY_IS_STUDENT, isStudent).apply();
    }

    @Override
    public int isStudent() {
        return mPrefs.getInt(KEY_IS_STUDENT, 1);
    }

    @Override
    public void setParticipantId(int id) {
        mPrefs.edit().putInt(KEY_PARTICIPANT_ID, id).apply();
    }

    @Override
    public int getParticipantId() {
        return mPrefs.getInt(KEY_PARTICIPANT_ID, 1);
    }

    @Override
    public void setName(String name) {
        mPrefs.edit().putString(KEY_NAME, name).apply();
    }

    @Override
    public String getName() {
        return mPrefs.getString(KEY_NAME, "");
    }

    @Override
    public boolean isStepOneDone() {
        return mPrefs.getBoolean(KEY_STEP1, false);
    }

    @Override
    public void setStepOneDone(boolean isDone) {
        mPrefs.edit().putBoolean(KEY_STEP1, isDone).apply();
    }

    @Override
    public boolean isStepTwoDone() {
        return mPrefs.getBoolean(KEY_STEP2, false);
    }

    @Override
    public void setStepTwoDone(boolean isDoness) {
        mPrefs.edit().putBoolean(KEY_STEP2, isDoness).apply();
    }
}
