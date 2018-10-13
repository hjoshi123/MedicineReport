package me.blume.medicinereport;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import me.blume.medicinereport.utils.PermissionUtils;

public class FormRequest {
	private String mMessage;
	private Activity mActivity;
	private SmsManager manager;

	public FormRequest(Activity activity, String message, SmsManager manager) {
		this.mMessage = message;
		this.mActivity = activity;
		this.manager = manager;
	}

	protected void sendSMSOffline(final String phoneNumber) {
		PermissionUtils.checkPermission(mActivity, Manifest.permission.SEND_SMS,
			new PermissionUtils.PermissionAskListener() {
				@Override
				public void onNeedPermission() {
					ActivityCompat.requestPermissions(mActivity,
							new String[]{Manifest.permission.READ_SMS}, 1);
				}

				@Override
				public void onPermissionPreviouslyDenied() {

				}

				@Override
				public void onPermissionDisabled() {

				}

				@Override
				public void onPermissionGranted() {
					try {
						Log.d("SMS", phoneNumber);
						manager.sendTextMessage(phoneNumber, null, mMessage, null, null);
						Toast.makeText(mActivity, "Message Sent",
								Toast.LENGTH_LONG).show();
					} catch (Exception ex) {
						Toast.makeText(mActivity,"Message not sent",
								Toast.LENGTH_LONG).show();
						ex.printStackTrace();
					}
				}
			});
	}
}
