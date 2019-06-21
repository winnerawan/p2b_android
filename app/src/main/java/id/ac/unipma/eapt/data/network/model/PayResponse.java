package id.ac.unipma.eapt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("payment")
    @Expose
    private Pay payment;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Pay getPayment() {
        return payment;
    }

    public void setPayment(Pay payment) {
        this.payment = payment;
    }
}
