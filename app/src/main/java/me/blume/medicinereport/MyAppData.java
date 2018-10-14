package me.blume.medicinereport;

import android.app.Application;

public class MyAppData extends Application {

    DrugPOJO drugPOJO;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public DrugPOJO getDrugPOJO() {
        return drugPOJO;
    }

    public void setDrugPOJO(DrugPOJO drugPOJO) {
        this.drugPOJO = drugPOJO;
    }
}
