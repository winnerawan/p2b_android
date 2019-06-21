package id.ac.unipma.eapt.data.network.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountStudentResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("student")
    @Expose
    private List<Student> student = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }
}
