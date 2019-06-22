package id.ac.unipma.eapt.ui.login;

import com.androidnetworking.error.ANError;
import com.google.firebase.iid.FirebaseInstanceId;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.data.DataManager;
import id.ac.unipma.eapt.data.network.model.TokenResponse;
import id.ac.unipma.eapt.ui.base.BasePresenter;
import id.ac.unipma.eapt.utils.AppLogger;
import id.ac.unipma.eapt.utils.CommonUtils;
import id.ac.unipma.eapt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

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
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.message_invalid_email);
            return;
        }
        if (pass.isEmpty()) {
            getMvpView().onError(R.string.message_empty_password);
            return;
        }
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager().login(email, pass)
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(resp -> {
                    getMvpView().hideLoading();

                    if (resp.getError()) {
                        getMvpView().onError(R.string.wrong_auth);
                        return;
                    }
                    saveCredentials(resp.getParticipant().get(0).getId(), resp.getParticipant().get(0).getEmail(), resp.getParticipant().get(0).getIsStudent());
                    getDataManager().setLoggedIn(true);
                    getMvpView().gotoMainActivity();
                    getDataManager().setLoggedIn(true);
                }, throwable -> {
                    getMvpView().hideLoading();

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


    public void saveCredentials(int id, String name,int isStudent) {
        getDataManager().setParticipantId(id);
        getDataManager().setName(name);
        getDataManager().setIsStudent(isStudent);
        sendtoken(id, FirebaseInstanceId.getInstance().getToken());
    }

    private void sendtoken(int participant_id, String token) {
        if (token!=null) {
            getCompositeDisposable().add(getDataManager().sendToken(participant_id, token)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(tokenResponse -> {

                    }, throwable -> {
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }));
        }
    }
}
