package id.ac.unipma.eapt.data.network;

import id.ac.unipma.eapt.data.network.model.LoginResponse;
import id.ac.unipma.eapt.data.network.model.Resp;
import io.reactivex.Single;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public interface ApiHelper {

    Single<LoginResponse> login(String email, String password);
    Single<Resp> register(String email, String password, int type);
}
