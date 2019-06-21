package id.ac.unipma.eapt.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pay {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("participant_id")
    @Expose
    private Integer participantId;
    @SerializedName("bank_id")
    @Expose
    private Integer bankId;
    @SerializedName("no_ref")
    @Expose
    private String noRef;

    @SerializedName("proof_image")
    @Expose
    private String proofImage;
    @SerializedName("status")
    @Expose
    private Integer status;

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

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getNoRef() {
        return noRef;
    }

    public void setNoRef(String noRef) {
        this.noRef = noRef;
    }

    public String getProofImage() {
        return proofImage;
    }

    public void setProofImage(String proofImage) {
        this.proofImage = proofImage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
