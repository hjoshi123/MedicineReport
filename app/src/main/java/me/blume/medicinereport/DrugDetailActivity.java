package me.blume.medicinereport;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DrugDetailActivity extends AppCompatActivity {

    DrugPOJO drugPOJO;
    TextView drug_name,strength,dosage,medium,lot,expiry;
    Button report;
    RelativeLayout rl;
    Boolean is_valid;
    RespinsePOJO respinsePOJO;


    private Retrofit.Builder builder;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);

        Activity activity = this;


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        builder = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.lot_url))
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();


        drugPOJO = ((MyAppData)getApplicationContext()).getDrugPOJO();

        TextView hyper_link = findViewById(R.id.hyperlink);
        hyper_link.setClickable(true);
        hyper_link.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href="+drugPOJO.getLink()+"'> Official Site </a>";
        hyper_link.setText(Html.fromHtml(text));

        rl = findViewById(R.id.rl);

        Retro userClient = retrofit.create(Retro.class);
        Map<String,String> map = new HashMap<>();
        map.put("lot",drugPOJO.getLot());
        Call<RespinsePOJO> call = userClient.lotValidate("application/x-www-form-urlencoded",map);
        call.enqueue(new Callback<RespinsePOJO>() {
            @Override
            public void onResponse(Call<RespinsePOJO> call, Response<RespinsePOJO> response) {
                String lot_val = drugPOJO.getLot();
                Log.i("lot val",response.body().getAllow()+"");
                respinsePOJO = response.body();
                lot.setText(respinsePOJO.getAllow()?lot_val+getResources().getString(R.string.right):lot_val+getResources().getString(R.string.cross));
            }

            @Override
            public void onFailure(Call<RespinsePOJO> call, Throwable t) {

            }
        });

        drug_name = findViewById(R.id.drug_name);
        strength = findViewById(R.id.strength);
        dosage = findViewById(R.id.req_dosage);
        medium = findViewById(R.id.medium);
        lot = findViewById(R.id.lot);
        expiry = findViewById(R.id.expiry);



        drug_name.setText(drugPOJO.getName());
        strength.setText(drugPOJO.getStrength());
        dosage.setText(drugPOJO.getRecDosage());
        medium.setText(drugPOJO.getMedium());
        //lotValidation(lot_val)?lot_val+"":lot_val+""
        String expiry_val = drugPOJO.getExpiry();
        //expiry_val)?expiry_val+"":expiry_val+""
        //dateValidation(expiry_val)?expiry_val+"x ":expiry_val+" `"
        expiry.setText(expiry_val);

        report = findViewById(R.id.report);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        Picasso.get().load(drugPOJO.getImgsrc()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                rl.setBackground(new BitmapDrawable(bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });


    }

    public void lotValidation(final String lot) {


    }

    public boolean dateValidation(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date before = null,today;
        try {
            before = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        today = Calendar.getInstance().getTime();
        try {
            today = simpleDateFormat.parse(String.valueOf(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(today.after(before))
            return true;
        else return false;
    }



}
