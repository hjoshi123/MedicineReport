package me.blume.medicinereport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespinsePOJO {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("allow")
    @Expose
    private Boolean allow;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getAllow() {
        return allow;
    }

    public void setAllow(Boolean allow) {
        this.allow = allow;
    }


}
