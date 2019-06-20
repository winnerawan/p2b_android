package id.ac.unipma.eapt.data.network.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProgramStudyResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("programs")
    @Expose
    private List<Program> programs = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }
}
