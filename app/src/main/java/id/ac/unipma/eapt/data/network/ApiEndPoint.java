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
    public static final String ENDPOINT_COMICS = API_URL + "/comics";
    public static final String ENDPOINT_NEW_CHAPTERS = API_URL + "/newchapters";
    public static final String ENDPOINT_CHAPTERS = API_URL + "/comic/";
    public static final String ENDPOINT_GENRES= API_URL + "/genres";
    public static final String ENDPOINT_BY_GENRE= API_URL + "/comics/";
    public static final String ENDPOINT_SEARCH= API_URL + "/search";
    public static final String ENDPOINT_APP= API_URL + "/app";
    public static final String ENDPOINT_POPULARS= API_URL + "/populars";
    public static final String ENDPOINT_CONTENT = "https://api." + BuildConfig.BASE_URL + "/scraper/index.php";

}