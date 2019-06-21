package id.ac.unipma.eapt.data.network.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("banks")
    @Expose
    private List<Bank> banks = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

}
