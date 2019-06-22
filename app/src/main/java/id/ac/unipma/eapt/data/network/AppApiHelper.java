package id.ac.unipma.eapt.data.network;


import com.rx2androidnetworking.Rx2AndroidNetworking;
import id.ac.unipma.eapt.data.network.model.*;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;


/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {

    }

    @Override
    public Single<LoginResponse> login(String email, String password) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGIN)
                .addQueryParameter("email", email)
                .addQueryParameter("password", password)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<Resp> register(String email, String password, int type) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_REGISTER)
                .addQueryParameter("email", email)
                .addQueryParameter("password", password)
                .addQueryParameter("is_student", String.valueOf(type))
                .build()
                .getObjectSingle(Resp.class);
    }

    @Override
    public Single<AccountStudentResponse> getInfoStudent(int participant_id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_INFO_STUDENT + participant_id)
                .build()
                .getObjectSingle(AccountStudentResponse.class);
    }

    @Override
    public Single<AccountGeneralResponse> getInfoGeneral(int participant_id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_INFO_GENERAL + participant_id)
                .build()
                .getObjectSingle(AccountGeneralResponse.class);
    }

    @Override
    public Single<BankResponse> getBanks() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BANK)
                .build()
                .getObjectSingle(BankResponse.class);
    }

    @Override
    public Single<ProgramStudyResponse> getPrograms() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_PROGRAM)
                .build()
                .getObjectSingle(ProgramStudyResponse.class);
    }

    @Override
    public Single<InputStudentResponse> inputDataStudent(int participant_id, int program_id, String no_reg, String nim, String fullname, String dob) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_INPUT_STUDENT)
                .addQueryParameter("participant_id", String.valueOf(participant_id))
                .addQueryParameter("program_id", String.valueOf(program_id))
                .addQueryParameter("no_reg", no_reg)
                .addQueryParameter("nim", nim)
                .addQueryParameter("fullname", fullname)
                .addQueryParameter("dob", dob)
                .build()
                .getObjectSingle(InputStudentResponse.class);
    }


    //todo: general input


    @Override
    public Single<PayResponse> pay(int participant_id, int bank_id, String no_ref, File proof_image, int status) {
        return Rx2AndroidNetworking.upload(ApiEndPoint.ENDPOINT_PAY)
                .addMultipartParameter("participant_id", String.valueOf(participant_id))
                .addMultipartParameter("bank_id", String.valueOf(bank_id))
                .addMultipartParameter("no_ref", no_ref)
                .addMultipartFile("proof_image", proof_image)
                .addMultipartParameter("status", String.valueOf(0))
                .build()
                .getObjectSingle(PayResponse.class);
    }


    @Override
    public Single<CheckPaymentResponse> checkPayment(int participant_id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_CHECK + participant_id)
                .build()
                .getObjectSingle(CheckPaymentResponse.class);
    }

    @Override
    public Single<InputStudentResponse> inputDataGeneral(int participant_id, String no_reg, String nik, String fullname, String dob, String phone) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_INPUT_GENERAL)
                .addQueryParameter("participant_id", String.valueOf(participant_id))
                .addQueryParameter("no_reg", no_reg)
                .addQueryParameter("nik", nik)
                .addQueryParameter("fullname", fullname)
                .addQueryParameter("dob", dob)
                .addQueryParameter("phone", phone)
                .build()
                .getObjectSingle(InputStudentResponse.class);
    }

    @Override
    public Single<TokenResponse> sendToken(int participant_id, String token) {
        return Rx2AndroidNetworking.put(ApiEndPoint.ENDPOINT_TOKEN)
                .addQueryParameter("participant_id", String.valueOf(participant_id))
                .addQueryParameter("fcm_token", token)
                .build()
                .getObjectSingle(TokenResponse.class);
    }
}
