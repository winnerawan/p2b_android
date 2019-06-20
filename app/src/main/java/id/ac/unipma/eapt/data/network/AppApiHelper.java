package id.ac.unipma.eapt.data.network;


import com.rx2androidnetworking.Rx2AndroidNetworking;
import id.ac.unipma.eapt.data.network.model.LoginResponse;
import id.ac.unipma.eapt.data.network.model.Resp;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;


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
}
