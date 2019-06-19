package id.ac.unipma.eapt.ui.login;

import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.data.DataManager;
import id.ac.unipma.eapt.ui.base.BasePresenter;
import id.ac.unipma.eapt.utils.CommonUtils;
import id.ac.unipma.eapt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class LoginPresenter<V extends LoginView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    @Inject
    public LoginPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void signIn(String email, String pass) {
        if (email.isEmpty()) {
            getMvpView().onError(R.string.message_empty_email);
        }
        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.message_invalid_email);
        }
        if (pass.isEmpty()) {
            getMvpView().onError(R.string.message_empty_password);
        }


    }
}
