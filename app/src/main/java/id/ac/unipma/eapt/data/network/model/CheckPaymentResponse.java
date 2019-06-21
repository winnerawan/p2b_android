package id.ac.unipma.eapt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckPaymentResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("is_done")
    @Expose
    private Boolean isDone;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }
}
