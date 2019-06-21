package id.ac.unipma.eapt.ui.student;

import id.ac.unipma.eapt.data.network.model.Program;
import id.ac.unipma.eapt.ui.base.MvpView;

import java.util.List;

public interface StudentView extends MvpView {
    void showPrograms(List<Program> programs);

    void back();
}
