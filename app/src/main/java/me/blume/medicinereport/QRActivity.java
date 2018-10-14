package me.blume.medicinereport;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QRActivity extends AppCompatActivity {

	private Retrofit.Builder builder;
	private Retrofit retrofit;
	JSONObject jsonObject;
	String jsonString;
	public final static int QRcodeWidth = 500 ;
	Bitmap bitmap ;
	IntentIntegrator qrscan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qr);

		final Activity activity = this;

		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		httpClient.addInterceptor(logging);
		builder = new Retrofit.Builder()
				.baseUrl("http://requestbin.fullcontact.com/")
				.client(httpClient.build())
				.addConverterFactory(GsonConverterFactory.create());
		retrofit = builder.build();

		qrscan = new IntentIntegrator(activity);
		qrscan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
		qrscan.setPrompt("Scan");
		qrscan.setCameraId(0);
		qrscan.setBeepEnabled(true);
		qrscan.setOrientationLocked(true);
		qrscan.setBarcodeImageEnabled(false);
		qrscan.setCaptureActivity(CaptureActivit.class);
		qrscan.initiateScan();

	}

	public void sendCodedString(String coded){
		Retro userClient = retrofit.create(Retro.class);
		Call<String> call = userClient.sendCoded(coded);
		call.enqueue(new Callback<String>() {
			@Override
			public void onResponse(Call<String> call, Response<String> response) {
				if(response.isSuccessful()){
					Toast.makeText(QRActivity.this, "Successfull.", Toast.LENGTH_SHORT).show();
				}else {

				}
			}

			@Override
			public void onFailure(Call<String> call, Throwable t) {

			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if (result != null) {
			if (result.getContents() == null) {
				Toast.makeText(this, "Medical record not found!!", Toast.LENGTH_SHORT).show();
			} else {
				Log.i("adfadf",result.getContents());
				Gson g = new Gson();
				((MyAppData)getApplicationContext()).setDrugPOJO(g.fromJson(result.getContents(),DrugPOJO.class));
				startActivity(new Intent(this,DrugDetailActivity.class));
			}
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	private Bitmap TextToImageEncode(String Value) throws WriterException {
		BitMatrix bitMatrix;
		try {
			bitMatrix = new MultiFormatWriter().encode(
					Value,
					BarcodeFormat.DATA_MATRIX.QR_CODE,
					QRcodeWidth, QRcodeWidth, null
			);

		} catch (IllegalArgumentException Illegalargumentexception) {

			return null;
		}
		int bitMatrixWidth = bitMatrix.getWidth();

		int bitMatrixHeight = bitMatrix.getHeight();

		int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

		for (int y = 0; y < bitMatrixHeight; y++) {
			int offset = y * bitMatrixWidth;

			for (int x = 0; x < bitMatrixWidth; x++) {

				pixels[offset + x] = bitMatrix.get(x, y) ?
						getResources().getColor(R.color.black):getResources().getColor(R.color.white);
			}
		}
		Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

		bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
		return bitmap;
	}
}
