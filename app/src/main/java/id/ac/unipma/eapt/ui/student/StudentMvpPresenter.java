package id.ac.unipma.eapt.ui.student;

import id.ac.unipma.eapt.ui.base.MvpPresenter;

public interface StudentMvpPresenter<V extends StudentView> extends MvpPresenter<V> {

    void getPrograms();

    void sendData(int program_id, String no_reg, String nim, String fullname, String dob);

}
