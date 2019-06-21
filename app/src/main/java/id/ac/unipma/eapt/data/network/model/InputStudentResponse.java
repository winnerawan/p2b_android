package id.ac.unipma.eapt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InputStudentResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("student")
    @Expose
    private Student student;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
