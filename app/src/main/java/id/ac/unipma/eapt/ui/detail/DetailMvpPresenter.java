package id.ac.unipma.eapt.ui.detail;

import id.ac.unipma.eapt.ui.base.MvpPresenter;

public interface DetailMvpPresenter<V extends DetailView> extends MvpPresenter<V> {


    String getIntersId();
}
