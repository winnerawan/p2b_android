package id.ac.unipma.eapt.ui.student;

import android.os.Bundle;
import butterknife.ButterKnife;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.ui.base.BaseActivity;

import javax.inject.Inject;

public class StudentActivity extends BaseActivity implements StudentView {

    @Inject
    StudentMvpPresenter<StudentView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {

    }
}
