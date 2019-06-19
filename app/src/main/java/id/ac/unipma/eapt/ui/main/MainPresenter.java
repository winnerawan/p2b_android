package id.ac.unipma.eapt.ui.main;

import id.ac.unipma.eapt.data.DataManager;
import id.ac.unipma.eapt.utils.rx.SchedulerProvider;
import id.ac.unipma.eapt.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class MainPresenter<V extends MainView> extends BasePresenter<V> implements MainMvpPresenter<V>{

    @Inject
    public MainPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
