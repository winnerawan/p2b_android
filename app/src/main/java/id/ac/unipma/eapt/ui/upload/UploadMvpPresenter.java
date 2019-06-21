package id.ac.unipma.eapt.ui.upload;

import id.ac.unipma.eapt.ui.base.MvpPresenter;

import java.io.File;

public interface UploadMvpPresenter<V extends UploadView> extends MvpPresenter<V> {

    void sendProofImage(int bank_id, String no_ref, File proof_image, int status);
    void loadBanks();
}
