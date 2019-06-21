package id.ac.unipma.eapt.ui.upload;

import com.androidnetworking.error.ANError;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.data.DataManager;
import id.ac.unipma.eapt.data.network.model.PayResponse;
import id.ac.unipma.eapt.ui.base.BasePresenter;
import id.ac.unipma.eapt.utils.AppLogger;
import id.ac.unipma.eapt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import javax.inject.Inject;
import java.io.File;

public class UploadPresenter<V extends UploadView> extends BasePresenter<V> implements UploadMvpPresenter<V> {

    @Inject
    public UploadPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void sendProofImage(int bank_id, String no_ref, File proof_image, int status) {
        getMvpView().showLoading();
        int participant = getDataManager().getParticipantId();
        if (no_ref.isEmpty()) {
            getMvpView().onError(R.string.error_empty_no_ref);
            return;
        }
        if (proof_image == null) {
            getMvpView().onError(R.string.error_empty_image);
            return;
        }

        getCompositeDisposable().add(getDataManager().pay(participant, bank_id, no_ref, proof_image, 0)
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(payResponse -> {
                    getMvpView().hideLoading();

                    if (payResponse.getError()) {
                        getMvpView().onError(R.string.some_error);
                        return;
                    }
                    getDataManager().setStepTwoDone(true);
                    getMvpView().showMessage(R.string.success_send_payment);
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

    @Override
    public void loadBanks() {

        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager().getBanks()
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(bankResponse -> {
                    getMvpView().hideLoading();
                    if (bankResponse.getError()) {
                        return;
                    }
                    getMvpView().showBanks(bankResponse.getBanks());
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
