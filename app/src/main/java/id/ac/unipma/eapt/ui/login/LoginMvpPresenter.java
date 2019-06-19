package id.ac.unipma.eapt.ui.login;

import id.ac.unipma.eapt.ui.base.MvpPresenter;

public interface LoginMvpPresenter<V extends LoginView> extends MvpPresenter<V> {
    void signIn(String toString, String toString1);
}
