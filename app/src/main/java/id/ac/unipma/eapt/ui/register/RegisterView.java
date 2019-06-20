package id.ac.unipma.eapt.ui.register;

import id.ac.unipma.eapt.ui.base.MvpView;

import java.util.List;

public interface RegisterView extends MvpView {

    void showType(List<AccountType> accountTypes);

    void gotoLogin();
}
