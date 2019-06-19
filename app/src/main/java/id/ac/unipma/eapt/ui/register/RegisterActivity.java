package id.ac.unipma.eapt.ui.register;

import android.os.Bundle;
import butterknife.ButterKnife;
import id.ac.unipma.eapt.ui.base.BaseActivity;

import javax.inject.Inject;

public class RegisterActivity extends BaseActivity implements RegisterView {

    @Inject
    RegisterMvpPresenter<RegisterView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(id.ac.unipma.eapt.R.layout.activity_register);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {

    }
}
