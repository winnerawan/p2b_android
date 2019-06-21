package id.ac.unipma.eapt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("participant_id")
    @Expose
    private Integer participantId;
    @SerializedName("program_id")
    @Expose
    private Integer programId;
    @SerializedName("no_reg")
    @Expose
    private String noReg;
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("program_study")
    @Expose
    private Object programStudy;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public String getNoReg() {
        return noReg;
    }

    public void setNoReg(String noReg) {
        this.noReg = noReg;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Object getProgramStudy() {
        return programStudy;
    }

    public void setProgramStudy(Object programStudy) {
        this.programStudy = programStudy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
