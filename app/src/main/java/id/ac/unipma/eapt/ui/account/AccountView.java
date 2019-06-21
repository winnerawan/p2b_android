package id.ac.unipma.eapt.ui.account;

import id.ac.unipma.eapt.ui.base.MvpView;

public interface AccountView extends MvpView {
    void gotoStudent();

    void gotoGeneral();

    void showEmail(String email);
}
