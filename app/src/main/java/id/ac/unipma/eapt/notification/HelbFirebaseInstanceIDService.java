/*
 * Copyright (c) 2018. Winnerawan T - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential|
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2018
 */

package id.ac.unipma.eapt.notification;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.gson.Gson;
import id.ac.unipma.eapt.data.network.ApiEndPoint;
import id.ac.unipma.eapt.utils.AppConstants;
import id.ac.unipma.eapt.utils.AppLogger;
import org.json.JSONObject;


public class HelbFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = HelbFirebaseInstanceIDService.class.getSimpleName();
    private String KEY_PARTICIPANT_ID = "KEY_PARTICIPANT_ID";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // Saving reg id to shared preferences

        // sending reg id to your server
        if (getParticipantId()!=0) {
            sendFcmTokenToServer(refreshedToken);
        }
        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent("KEY_REG_COMPLETE");
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendFcmTokenToServer(String token) {
//        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, 0);
//        String api_token = pref.getString(KEY_TOKEN, "");
//        Log.e(TAG, "sendRegistrationToServer: " + token);
//
        AndroidNetworking.put(ApiEndPoint.ENDPOINT_TOKEN)
                .addQueryParameter("participant_id", String.valueOf(getParticipantId()))
                .addQueryParameter("fcm_token", token)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        AppLogger.e(new Gson().toJson(response));
                    }

                    @Override
                    public void onError(ANError anError) {
                        AppLogger.e(" Error Body = " + anError.getErrorBody());
                        AppLogger.e(" Message = " + anError.getMessage());
                        AppLogger.e(" Response = " + anError.getResponse());
                        AppLogger.e(" Error Code = " + anError.getErrorCode());
                    }
                });
    }

    private int getParticipantId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(AppConstants.PREF_NAME, 0);

        AppLogger.e("participant id (notification) =" + pref.getInt(KEY_PARTICIPANT_ID, 1));

        return pref.getInt(KEY_PARTICIPANT_ID, 0);

    }
}
