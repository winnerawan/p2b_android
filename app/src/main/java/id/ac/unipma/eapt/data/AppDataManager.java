package id.ac.unipma.eapt.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.ac.unipma.eapt.data.db.DbHelper;
import id.ac.unipma.eapt.data.network.ApiHelper;
import id.ac.unipma.eapt.data.network.model.*;
import id.ac.unipma.eapt.data.prefs.PreferencesHelper;
import id.ac.unipma.eapt.di.ApplicationContext;
import io.reactivex.Single;

import java.io.File;

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

    @Override
    public void setParticipantId(int id) {
        mPreferencesHelper.setParticipantId(id);
    }

    @Override
    public int getParticipantId() {
        return mPreferencesHelper.getParticipantId();
    }

    @Override
    public void setName(String name) {
        mPreferencesHelper.setName(name);
    }

    @Override
    public String getName() {
        return mPreferencesHelper.getName();
    }

    @Override
    public Single<AccountStudentResponse> getInfoStudent(int participant_id) {
        return mApiHelper.getInfoStudent(participant_id);
    }

    @Override
    public Single<AccountGeneralResponse> getInfoGeneral(int participant_id) {
        return mApiHelper.getInfoGeneral(participant_id);
    }

    @Override
    public Single<BankResponse> getBanks() {
        return mApiHelper.getBanks();
    }

    @Override
    public Single<ProgramStudyResponse> getPrograms() {
        return mApiHelper.getPrograms();
    }

    @Override
    public Single<InputStudentResponse> inputDataStudent(int participant_id, int program_id, String no_reg, String nim, String fullname, String dob) {
        return mApiHelper.inputDataStudent(participant_id, program_id, no_reg, nim, fullname, dob);
    }

    @Override
    public boolean isStepOneDone() {
        return mPreferencesHelper.isStepOneDone();
    }

    @Override
    public void setStepOneDone(boolean isDone) {
        mPreferencesHelper.setStepOneDone(isDone);
    }

    @Override
    public boolean isStepTwoDone() {
        return mPreferencesHelper.isStepTwoDone();
    }

    @Override
    public void setStepTwoDone(boolean isDone) {
        mPreferencesHelper.setStepTwoDone(isDone);
    }

    @Override
    public Single<PayResponse> pay(int participant_id, int bank_id, String no_ref, File proof_image, int status) {
        return mApiHelper.pay(participant_id, bank_id, no_ref, proof_image, status);
    }

    @Override
    public Single<CheckPaymentResponse> checkPayment(int participant_id) {
        return mApiHelper.checkPayment(participant_id);
    }

    @Override
    public Single<InputStudentResponse> inputDataGeneral(int participant_id, String no_reg, String nik, String fullname, String dob, String phone) {
        return mApiHelper.inputDataGeneral(participant_id, no_reg, nik, fullname, dob, phone);
    }

    @Override
    public Single<TokenResponse> sendToken(int participant_id, String token) {
        return mApiHelper.sendToken(participant_id, token);
    }
}


