package id.ac.unipma.eapt.ui.general;

import com.androidnetworking.error.ANError;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.data.DataManager;
import id.ac.unipma.eapt.ui.base.BasePresenter;
import id.ac.unipma.eapt.utils.AppLogger;
import id.ac.unipma.eapt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;


public class GeneralPresenter<V extends GeneralView> extends BasePresenter<V> implements GeneralMvpPresenter<V> {

    @Inject
    public GeneralPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void sendData(String no_reg, String nik, String fullname, String dob, String phone) {
        getMvpView().showLoading();

        int participant_id = getDataManager().getParticipantId();
        if (nik.isEmpty()) {
            getMvpView().onError(R.string.error_empty_nik);
            return;
        }
        if (fullname.isEmpty()) {
            getMvpView().onError(R.string.error_empty_fullname);
            return;
        }
        if (no_reg.isEmpty()) {
            getMvpView().onError(R.string.error_empty_noreg);
            return;
        }
        if (dob.isEmpty()) {
            getMvpView().onError(R.string.error_empty_dob);
        }

        getCompositeDisposable().add(getDataManager().inputDataGeneral(participant_id, no_reg, nik, fullname, dob, phone)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(inputStudentResponse -> {
                    getMvpView().hideLoading();

                    if (inputStudentResponse.getError()) {
                        getMvpView().onError(R.string.some_error);
                        return;
                    }
                    getDataManager().setStepOneDone(true);
                    getMvpView().showMessage(R.string.success_input);
                    getMvpView().back();
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
