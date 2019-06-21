package id.ac.unipma.eapt.ui.account;

import id.ac.unipma.eapt.ui.base.MvpPresenter;

public interface AccountMvpPresenter<V extends AccountView> extends MvpPresenter<V> {

    void decideActivty();
    void getInfo();
    void showEmail();

    void logout();

    void decideActivty2();

    void checkPayment();
}
