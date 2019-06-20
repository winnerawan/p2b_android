package id.ac.unipma.eapt.data.network;

import id.ac.unipma.eapt.BuildConfig;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public class ApiEndPoint {

    public static final String API_URL = "https://manga." + BuildConfig.BASE_URL + "/api";
    public static final String ENDPOINT_REGISTER = API_URL + "/register";
    public static final String ENDPOINT_LOGIN = API_URL + "/login";

}