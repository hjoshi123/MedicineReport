package me.blume.medicinereport.utils;

import android.app.Activity;
import android.content.Context;
import android.telephony.SmsManager;

import me.blume.medicinereport.FormRequest;

public class SendSMS {


    public static void send(String body, Activity activity) {
        FormRequest mFormRequest;
        mFormRequest = new FormRequest(activity, body, SmsManager.getDefault());
        mFormRequest.sendSMSOffline("7889145957");
    }
}
