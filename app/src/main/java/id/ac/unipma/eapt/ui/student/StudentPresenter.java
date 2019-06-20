package id.ac.unipma.eapt.ui.student;

import id.ac.unipma.eapt.data.DataManager;
import id.ac.unipma.eapt.ui.base.BasePresenter;
import id.ac.unipma.eapt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class StudentPresenter<V extends StudentView> extends BasePresenter<V> implements StudentMvpPresenter<V> {

    @Inject
    public StudentPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
