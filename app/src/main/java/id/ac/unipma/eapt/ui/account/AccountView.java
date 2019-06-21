package id.ac.unipma.eapt.ui.account;

import id.ac.unipma.eapt.data.network.model.Program;
import id.ac.unipma.eapt.ui.base.MvpView;

import java.util.List;

public interface AccountView extends MvpView {
    void gotoStudent();

    void gotoGeneral();

    void showEmail(String email);

    void gotoLogin();

    void gotoPayment();


    void showInfo(String fullname, String nim);
}
