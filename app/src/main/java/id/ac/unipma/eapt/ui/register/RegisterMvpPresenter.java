package id.ac.unipma.eapt.ui.register;

import id.ac.unipma.eapt.ui.base.MvpPresenter;

public interface RegisterMvpPresenter<V extends RegisterView> extends MvpPresenter<V> {

    void showAccountType();
    void register(String email, String password, int type);
}
