package id.ac.unipma.eapt.ui.general;

import id.ac.unipma.eapt.ui.base.MvpPresenter;

public interface GeneralMvpPresenter<V extends GeneralView> extends MvpPresenter<V> {

    void sendData(String no_reg, String nik, String fullname, String dob, String phone);
}
