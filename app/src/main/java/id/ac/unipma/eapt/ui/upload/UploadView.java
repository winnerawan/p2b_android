package id.ac.unipma.eapt.ui.upload;

import id.ac.unipma.eapt.data.network.model.Bank;
import id.ac.unipma.eapt.ui.base.MvpView;

import java.util.List;

public interface UploadView extends MvpView {
    void back();
    void showBanks(List<Bank> banks);
}
