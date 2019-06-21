package id.ac.unipma.eapt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountGeneralResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("general")
    @Expose
    private List<General> generals = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<General> getGenerals() {
        return generals;
    }

    public void setGenerals(List<General> generals) {
        this.generals = generals;
    }
}
