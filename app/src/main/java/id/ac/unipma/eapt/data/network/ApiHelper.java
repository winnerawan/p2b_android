package id.ac.unipma.eapt.data.network;

import id.ac.unipma.eapt.data.network.model.*;
import io.reactivex.Single;

import java.io.File;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public interface ApiHelper {

    Single<LoginResponse> login(String email, String password);
    Single<Resp> register(String email, String password, int type);
    Single<AccountStudentResponse> getInfoStudent(int participant_id);
    Single<AccountGeneralResponse> getInfoGeneral(int participant_id);


    Single<BankResponse> getBanks();
    Single<ProgramStudyResponse> getPrograms();

    Single<InputStudentResponse> inputDataStudent(int participant_id, int program_id, String no_reg, String nim, String fullname, String dob);
    Single<InputStudentResponse> inputDataGeneral(int participant_id, String no_reg, String nik, String fullname, String dob, String phone);

    Single<PayResponse> pay(int participant_id, int bank_id, String no_ref, File proof_image, int status);

    Single<CheckPaymentResponse> checkPayment(int participant_id);
}
