package id.ac.unipma.eapt.ui.student;

import com.androidnetworking.error.ANError;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.data.DataManager;
import id.ac.unipma.eapt.data.network.model.InputStudentResponse;
import id.ac.unipma.eapt.ui.base.BasePresenter;
import id.ac.unipma.eapt.utils.AppLogger;
import id.ac.unipma.eapt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import javax.inject.Inject;

public class StudentPresenter<V extends StudentView> extends BasePresenter<V> implements StudentMvpPresenter<V> {

    @Inject
    public StudentPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getPrograms() {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager().getPrograms()
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(programStudyResponse -> {
                    getMvpView().hideLoading();

                    if (programStudyResponse.getError()) {
                        return;
                    }
                    getMvpView().showPrograms(programStudyResponse.getPrograms());
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

    @Override
    public void sendData(int program_id, String no_reg, String nim, String fullname, String dob) {

        int participant_id = getDataManager().getParticipantId();
        if (nim.isEmpty()) {
            getMvpView().onError(R.string.error_empty_nim);
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
            return;
        }
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager().inputDataStudent(participant_id, program_id, no_reg, nim, fullname, dob)
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
