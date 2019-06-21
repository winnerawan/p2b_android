package id.ac.unipma.eapt.ui.login;

import com.androidnetworking.error.ANError;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.data.DataManager;
import id.ac.unipma.eapt.ui.base.BasePresenter;
import id.ac.unipma.eapt.utils.AppLogger;
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
        getCompositeDisposable().add(getDataManager().login(email, pass)
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(resp -> {
                    if (resp.getError()) {
                        return;
                    }
                    saveCredentials(resp.getParticipant().get(0).getId(), resp.getParticipant().get(0).getEmail());
                    getDataManager().setLoggedIn(true);
                    getMvpView().gotoMainActivity();
                }, throwable -> {
                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        AppLogger.e(" Error Body = "+anError.getErrorBody());
                        AppLogger.e(" Message = "+anError.getMessage());
                        AppLogger.e(" Response = "+anError.getResponse());
                        AppLogger.e(" Error Code = "+anError.getErrorCode());
                        handleApiError(anError);
                    }

                }));

    }


    public void saveCredentials(int id, String name) {
        getDataManager().setParticipantId(id);
        getDataManager().setName(name);
    }
}
