package id.ac.unipma.eapt.ui.account;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.data.DataManager;
import id.ac.unipma.eapt.data.db.model.App;
import id.ac.unipma.eapt.data.network.model.CheckPaymentResponse;
import id.ac.unipma.eapt.data.network.model.ProgramStudyResponse;
import id.ac.unipma.eapt.ui.base.BasePresenter;
import id.ac.unipma.eapt.utils.AppLogger;
import id.ac.unipma.eapt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import javax.inject.Inject;

public class AccountPresenter<V extends AccountView> extends BasePresenter<V> implements AccountMvpPresenter<V> {

    private boolean isDone;
    @Inject
    public AccountPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void decideActivty() {
        boolean isDoneBefore = getDataManager().isStepOneDone();

        AppLogger.e("is done: "+isDoneBefore);

        if (getDataManager().isStudent() == 1) {
            if (isDoneBefore) {
                getMvpView().onError(R.string.already_registered);
            } else {
                getMvpView().gotoStudent();
            }
        } else {
            if (isDoneBefore) {
                getMvpView().onError(R.string.already_registered);
            } else {
                getMvpView().gotoGeneral();
            }
        }
    }

    @Override
    public void checkPayment() {
        getCompositeDisposable().add(getDataManager().checkPayment(getDataManager().getParticipantId())
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(checkPaymentResponse -> {
                    AppLogger.e(new Gson().toJson(checkPaymentResponse));
                    isDone = checkPaymentResponse.getIsDone();
                    getDataManager().setStepTwoDone(isDone);
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

    @Override
    public void decideActivty2() {
        boolean isDoneBefore = getDataManager().isStepTwoDone();
        AppLogger.e("is done bayar: "+isDoneBefore);
        if (!isDone) {
            getMvpView().gotoPayment();
        } else {
            getMvpView().onError(R.string.already_send);
        }
    }

    @Override
    public void showEmail() {
        getMvpView().showEmail(getDataManager().getName());
    }

    @Override
    public void getInfo() {
        int is_student = getDataManager().isStudent();
        if (is_student == 1) {
            getStudentInfo();
        } else {
            getGeneralInfo();
        }
    }

    private void getStudentInfo() {
        getCompositeDisposable().add(getDataManager().getInfoStudent(getDataManager().getParticipantId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(accountGeneralResponse -> {
                    if (accountGeneralResponse.getError()) {

                    } else {
                        if (accountGeneralResponse.getStudent() != null) {
                            if (accountGeneralResponse.getStudent().size() == 0) {
                                getDataManager().setStepOneDone(false);
                            } else {
                                getDataManager().setStepOneDone(true);
                                getMvpView().showInfo(
                                        accountGeneralResponse.getStudent().get(0).getFullname(),
                                        accountGeneralResponse.getStudent().get(0).getNim()
                                );
                            }
                        }
                    }
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

    private void getGeneralInfo() {
        getCompositeDisposable().add(getDataManager().getInfoGeneral(getDataManager().getParticipantId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(accountGeneralResponse -> {
                    if (accountGeneralResponse.getError()) {

                    } else {
                        if (accountGeneralResponse.getGenerals() != null) {
                            if (accountGeneralResponse.getGenerals().size() == 0) {
                                getDataManager().setStepOneDone(false);

                            } else {
                                getDataManager().setStepOneDone(true);
                                getMvpView().showInfo(
                                        accountGeneralResponse.getGenerals().get(0).getFullname(),
                                        accountGeneralResponse.getGenerals().get(0).getNik()
                                );
                            }
                        }
                    }
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

    @Override
    public void logout() {
        getDataManager().setLoggedIn(false);
        getDataManager().setStepOneDone(false);
        getDataManager().setStepTwoDone(false);
        getMvpView().gotoLogin();
    }
}
