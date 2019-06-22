package id.ac.unipma.eapt.data.network;

import id.ac.unipma.eapt.BuildConfig;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public class ApiEndPoint {

    public static final String API_URL = "http://eapt." + BuildConfig.BASE_URL + "/api";
    public static final String ENDPOINT_REGISTER = API_URL + "/register";
    public static final String ENDPOINT_LOGIN = API_URL + "/login";
    public static final String ENDPOINT_INFO_STUDENT = API_URL + "/studentprofile/";
    public static final String ENDPOINT_INFO_GENERAL = API_URL + "/generalprofile/";
    public static final String ENDPOINT_CHECK = API_URL + "/checkpayment/";

    public static final String ENDPOINT_TOKEN = API_URL + "/token";

    public static final String ENDPOINT_BANK = API_URL + "/banks";
    public static final String ENDPOINT_PROGRAM = API_URL + "/programs";


    public static final String ENDPOINT_PAY = API_URL + "/pay";

    public static final String ENDPOINT_INPUT_STUDENT = API_URL + "/student";
    public static final String ENDPOINT_INPUT_GENERAL = API_URL + "/general";



}