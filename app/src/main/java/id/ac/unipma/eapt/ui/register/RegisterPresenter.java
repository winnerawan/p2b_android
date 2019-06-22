package id.ac.unipma.eapt.ui.register;

import com.androidnetworking.error.ANError;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.data.DataManager;
import id.ac.unipma.eapt.data.network.model.Resp;
import id.ac.unipma.eapt.ui.base.BasePresenter;
import id.ac.unipma.eapt.utils.AppLogger;
import id.ac.unipma.eapt.utils.CommonUtils;
import id.ac.unipma.eapt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class RegisterPresenter<V extends RegisterView> extends BasePresenter<V> implements RegisterMvpPresenter<V> {

    @Inject
    public RegisterPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void showAccountType() {
        List<AccountType> types = new ArrayList<>();
        types.add(new AccountType(1, "MAHASISWA", "", "", ""));
        types.add(new AccountType(0, "UMUM", "", "", ""));

        getMvpView().showType(types);
    }

    @Override
    public void register(String email, String password, int type) {

        if (email.isEmpty()) {
            getMvpView().onError(R.string.message_empty_email);
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.message_invalid_email);
            return;
        }
        if (password.isEmpty()) {
            getMvpView().onError(R.string.message_empty_password);
            return;
        }
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager().register(email, password, type)
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(resp -> {
                    getMvpView().hideLoading();

                    if (resp.getError()) {
                        getMvpView().onError(R.string.some_error);
                        return;
                    }
                    getMvpView().showMessage(R.string.success_register);
                    getMvpView().gotoLogin();
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
}
